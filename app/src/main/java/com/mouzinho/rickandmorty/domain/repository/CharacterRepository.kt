package com.mouzinho.rickandmorty.domain.repository

import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import io.reactivex.Single

interface CharacterRepository {

    fun getAll(page: Int): Single<List<CharacterData>>
}