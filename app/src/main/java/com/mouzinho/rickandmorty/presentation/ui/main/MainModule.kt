package com.mouzinho.rickandmorty.presentation.ui.main

import com.mouzinho.rickandmorty.domain.states.CharacterState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun providesCharacterState() = CharacterState()
}