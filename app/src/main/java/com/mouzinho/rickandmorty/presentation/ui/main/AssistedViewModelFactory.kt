package com.mouzinho.rickandmorty.presentation.ui.main

import com.airbnb.mvrx.MvRxState

interface AssistedViewModelFactory<VM : MvRxViewModel<S>, S : MvRxState> {
    fun create(state: S): VM
}