package com.mouzinho.rickandmorty.presentation.base

import io.reactivex.Observable

interface BaseViewModel<I: Intent, S: ViewState> {
    fun processIntents(intent: Observable<I>)
    fun states(): Observable<S>
}