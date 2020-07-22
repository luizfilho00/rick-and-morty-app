package com.mouzinho.rickandmorty.domain.interactors

import com.mouzinho.rickandmorty.presentation.ui.base.Async
import com.mouzinho.rickandmorty.presentation.ui.base.Empty
import com.mouzinho.rickandmorty.presentation.ui.base.RxState

data class CounterState(val count: Async<Int> = Empty) : RxState