package br.com.mouzinho.rxarch

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class RxActivity<S : RxState> : AppCompatActivity(), RxStateViewListener {
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

    override fun onDestroy() {
        Log.d("RxState", "Activity Disposables disposed!")
        disposables.dispose()
        super.onDestroy()
    }
}