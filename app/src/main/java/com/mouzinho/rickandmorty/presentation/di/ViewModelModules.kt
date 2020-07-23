package com.mouzinho.rickandmorty.presentation.di

import com.google.gson.GsonBuilder
import com.mouzinho.rickandmorty.BuildConfig
import com.mouzinho.rickandmorty.data.repository.CharacterRepositoryImpl
import com.mouzinho.rickandmorty.data.service.ApiService
import com.mouzinho.rickandmorty.domain.interactors.GetCharacters
import com.mouzinho.rickandmorty.domain.interactors.GetCharactersImpl
import com.mouzinho.rickandmorty.domain.repository.CharacterRepository
import com.mouzinho.rickandmorty.presentation.ui.main.CharacterStateX
import com.mouzinho.rickandmorty.presentation.ui.main.MainXViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModules = module {
    val apiDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>()).build())
            .build()
    }

    single { HttpLoggingInterceptor().setLevel(get()) }

    single {
        GsonConverterFactory.create(
            GsonBuilder()
                .serializeNulls()
                .setDateFormat(apiDateFormat)
                .create()
        )
    }

    single {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    single { CharacterRepositoryImpl(get()) as CharacterRepository }
    single { CharacterStateX() }
    single { GetCharactersImpl(get()) as GetCharacters }

    viewModel { MainXViewModel(get(), get()) }
}