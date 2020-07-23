package br.com.mouzinho.rxarch

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

abstract class RxViewModel<S : RxState>(
    private val initialState: S
) : ViewModel() {
    internal val statePublisher = BehaviorSubject.createDefault<S>(initialState)
    internal val state get() = statePublisher.value!!

    private val disposables = CompositeDisposable()

    protected fun updateState(reducer: S.() -> S) {
        val state = reducer(initialState)
        statePublisher.onNext(state)
    }

    fun <T> Observable<T>.execute(stateReducer: S.(Async<T>) -> S) = execute({ it }, stateReducer)

    fun <T> Single<T>.execute(stateReducer: S.(Async<T>) -> S): Disposable = toObservable().execute({ it }, stateReducer)

    fun Completable.execute(stateReducer: S.(Async<Unit>) -> S): Disposable = toSingle { Unit }.execute(stateReducer)

    private fun <T, V> Observable<T>.execute(
        mapper: (T) -> V,
        stateReducer: S.(Async<V>) -> S
    ): Disposable {
        updateState { stateReducer(Loading()) }
        return map<Async<V>> { Success(mapper(it)) }
            .onErrorReturn { Error(it) }
            .subscribe { updateState { stateReducer(it) } }
            .disposeOnClear()
    }

    private fun Disposable.disposeOnClear(): Disposable {
        disposables.add(this)
        return this
    }

    override fun onCleared() {
        Log.d("RxState", "Viewmodel Disposables disposed!")
        disposables.dispose()
        super.onCleared()
    }
}