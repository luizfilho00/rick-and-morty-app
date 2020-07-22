package com.mouzinho.rickandmorty.presentation.ui.base

fun <A : RxViewModel<B>, B : RxState, C> withState(viewModel: A, block: (B) -> C) = block(viewModel.state)