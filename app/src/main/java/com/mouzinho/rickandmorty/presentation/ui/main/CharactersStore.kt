package com.mouzinho.rickandmorty.presentation.ui.main

import com.mouzinho.rickandmorty.presentation.base.Store
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class CharactersStore @Inject constructor() : Store<CharactersState> {

    override var reduce: BiFunction<CharactersState, CharactersState, CharactersState> =
        BiFunction { oldState: CharactersState, newState: CharactersState ->
            oldState.copy(characters = newState.characters, loading = newState.loading, error = newState.error)
        }
}