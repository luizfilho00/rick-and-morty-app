package com.mouzinho.rickandmorty.presentation.di

import com.mouzinho.rickandmorty.domain.interactors.GetCharacters
import com.mouzinho.rickandmorty.domain.interactors.GetCharactersImpl
import org.koin.dsl.module

val interactorModule = module {
    single { GetCharactersImpl(get()) as GetCharacters }
}