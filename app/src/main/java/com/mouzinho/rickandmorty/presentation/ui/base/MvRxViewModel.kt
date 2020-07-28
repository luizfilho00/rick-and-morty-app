package com.mouzinho.rickandmorty.presentation.ui.base

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.BuildConfig
import com.airbnb.mvrx.MvRxState

abstract class MvRxViewModel<S : MvRxState>(state: S) : BaseMvRxViewModel<S>(state, BuildConfig.DEBUG)