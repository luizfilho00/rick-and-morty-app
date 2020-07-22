package com.mouzinho.rickandmorty.presentation.ui.base

sealed class Async<out T>(val data: T? = null, val error: Throwable? = null) : RxState {
    open operator fun invoke(): T? = data
}

data class Loading<out T>(private val value: T? = null) : Async<T>()

class Error<out T>(error: Throwable) : Async<T>(error = error)

data class Success<out T>(val value: T) : Async<T>(data = value) {
    override operator fun invoke(): T = value
}

object Empty : Async<Nothing>()