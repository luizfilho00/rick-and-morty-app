package com.mouzinho.rickandmorty.presentation.ui.main

import com.mouzinho.rickandmorty.presentation.ui.base.CounterState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object MainXModule {

    @Provides
    @ActivityScoped
    fun providesCounterState() = CounterState()
}