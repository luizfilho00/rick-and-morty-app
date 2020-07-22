package com.mouzinho.rickandmorty.presentation.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.mouzinho.rickandmorty.domain.interactors.CounterState
import com.mouzinho.rickandmorty.presentation.ui.base.*
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainXActivity : RxActivity<CounterState>() {

    override val viewModel: MainXViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var x = 0
        Observable.interval(2, TimeUnit.SECONDS)
            .map { ++x * 2 }
            .subscribe({ viewModel.updateAsyncCount(it) }, {})
            .let(disposables::add)
    }

    override fun updateView() = withState(viewModel) { state ->
        Log.d(
            "RxState",
            when (state.count) {
                is Empty -> "Empty"
                is Loading -> "Loading"
                is Error -> state.count.error?.toString() ?: ""
                is Success -> state.count().toString()
            }
        )
    }
}