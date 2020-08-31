package com.mouzinho.rickandmorty.domain.repository

import com.mouzinho.rickandmorty.data.entity.response.CharacterResponse
import io.reactivex.Observable
import io.reactivex.Single

interface CharacterRepository {

    fun getFromPage(page: Int): Single<CharacterResponse>
}