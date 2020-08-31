package com.mouzinho.rickandmorty.presentation.base

import io.reactivex.functions.BiFunction


interface Store<S : ViewState> {

    var reduce: BiFunction<S, S, S>
}