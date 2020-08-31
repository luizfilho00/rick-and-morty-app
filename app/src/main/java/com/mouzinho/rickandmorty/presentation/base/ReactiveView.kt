package com.mouzinho.rickandmorty.presentation.base

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

interface ReactiveView<I : Intent, in S : ViewState> {
    val disposables: CompositeDisposable

    fun render(state: S)
    fun intents(): Observable<I>
    fun detach() {
        disposables.clear()
    }
}