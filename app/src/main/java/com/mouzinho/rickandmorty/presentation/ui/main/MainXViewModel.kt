package com.mouzinho.rickandmorty.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import com.mouzinho.rickandmorty.domain.interactors.CounterState
import com.mouzinho.rickandmorty.presentation.ui.base.RxViewModel
import com.mouzinho.rickandmorty.presentation.ui.base.Success
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class MainXViewModel @ViewModelInject constructor(
    state: CounterState
) : RxViewModel<CounterState>(state) {

    fun incrementCount(value: Int) = updateState { copy(count = Success(
        value
    )
    ) }

    fun updateAsyncCount(value: Int) {
        Observable.just(value)
            .delay(3, TimeUnit.SECONDS)
            .execute {
                copy(count = it)
            }
    }
}