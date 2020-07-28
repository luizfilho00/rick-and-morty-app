package com.mouzinho.rickandmorty.presentation.di

import com.mouzinho.rickandmorty.data.repository.CharacterRepositoryImpl
import com.mouzinho.rickandmorty.domain.repository.CharacterRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CharacterRepositoryImpl(get()) as CharacterRepository }
}