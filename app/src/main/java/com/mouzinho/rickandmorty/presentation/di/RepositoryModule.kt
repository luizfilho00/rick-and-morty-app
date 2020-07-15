package com.mouzinho.rickandmorty.presentation.di

import com.mouzinho.rickandmorty.data.repository.CharacterRepositoryImpl
import com.mouzinho.rickandmorty.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository
}