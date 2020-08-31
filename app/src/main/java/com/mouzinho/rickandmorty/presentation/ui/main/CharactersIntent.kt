package com.mouzinho.rickandmorty.presentation.ui.main

import com.mouzinho.rickandmorty.presentation.base.Intent

sealed class CharactersIntent : Intent {
    object LoadCharacters : CharactersIntent()
    object ClearCharacters : CharactersIntent()
}