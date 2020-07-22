package com.mouzinho.rickandmorty.presentation.ui.base

data class CounterState(val count: Async<Int> = Empty) : RxState