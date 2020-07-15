package com.mouzinho.rickandmorty.data.service

import com.mouzinho.rickandmorty.data.entity.response.CharacterResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character/")
    fun getCharacters(@Query("page") page: Int): Single<CharacterResponse>
}