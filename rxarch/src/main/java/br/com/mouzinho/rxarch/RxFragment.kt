package br.com.mouzinho.rxarch

import android.util.Log
import androidx.fragment.app.Fragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class RxFragment<S : RxState> : Fragment(), RxStateViewListener {
    abstract val viewModel: RxViewModel<S>
    protected val disposables = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        viewModel.statePublisher
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { updateView() },
                { Log.d("RxState", "Activity error -> $it") }
            )
            .let(disposables::add)
    }

    override fun onStop() {
        Log.d("RxState", "Activity Disposables cleared!")
        disposables.clear()
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("RxState", "Activity Disposables disposed!")
        disposables.dispose()
        super.onDestroyView()
    }
}