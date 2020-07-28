package com.mouzinho.rickandmorty.presentation

import android.app.Application
import com.mouzinho.rickandmorty.presentation.di.apiModule
import com.mouzinho.rickandmorty.presentation.di.interactorModule
import com.mouzinho.rickandmorty.presentation.di.repositoryModule
import com.mouzinho.rickandmorty.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RickAndMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyApp)
            androidLogger()
            modules(apiModule, repositoryModule, interactorModule, viewModelModule)
        }
    }
}