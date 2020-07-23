package com.mouzinho.rickandmorty.presentation

import android.app.Application
import com.mouzinho.rickandmorty.presentation.di.viewModelModules
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class RickAndMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyApp)
            androidLogger()
            modules(viewModelModules)
        }
    }
}