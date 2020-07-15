package com.mouzinho.rickandmorty.presentation.di

import com.mouzinho.rickandmorty.domain.interactors.GetCharacters
import com.mouzinho.rickandmorty.domain.interactors.GetCharactersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface InteractorModule {

    @Binds
    fun bindsCharacterInteractor(impl: GetCharactersImpl): GetCharacters
}