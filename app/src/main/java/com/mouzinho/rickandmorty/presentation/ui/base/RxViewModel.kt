package com.mouzinho.rickandmorty.presentation.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

abstract class RxViewModel<S : RxState>(private val initialState: S) : ViewModel() {
    val statePublisher = BehaviorSubject.createDefault<S>(initialState)
    internal val state get() = statePublisher.value!!
    private val disposables = CompositeDisposable()

    fun updateState(reducer: S.() -> S) {
        val state = reducer(initialState)
        statePublisher.onNext(state)
    }

    fun <T> Observable<T>.execute(stateReducer: S.(Async<T>) -> S) =
        execute({ it }, stateReducer)

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