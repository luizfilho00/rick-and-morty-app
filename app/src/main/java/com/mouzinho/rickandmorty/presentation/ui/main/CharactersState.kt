package com.mouzinho.rickandmorty.presentation.ui.main

import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import com.mouzinho.rickandmorty.presentation.base.ViewState

data class CharactersState(
    val characters: List<CharacterData>?,
    var loading: Boolean,
    var error: Throwable?
) : ViewState {

    companion object {
        fun idle() = CharactersState(emptyList(), true, null)
    }
}