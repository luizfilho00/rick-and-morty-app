package br.com.mouzinho.rxarch

fun <A : RxViewModel<B>, B : RxState, C> withState(viewModel: A, block: (B) -> C) = block(viewModel.state)