package com.mouzinho.rickandmorty.presentation.di

import com.mouzinho.rickandmorty.presentation.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}