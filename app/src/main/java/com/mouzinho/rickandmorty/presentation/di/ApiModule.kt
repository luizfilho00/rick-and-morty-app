package com.mouzinho.rickandmorty.presentation.di

import com.google.gson.GsonBuilder
import com.mouzinho.rickandmorty.BuildConfig
import com.mouzinho.rickandmorty.data.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
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
}