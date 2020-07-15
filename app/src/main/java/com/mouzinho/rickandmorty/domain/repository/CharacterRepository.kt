package com.mouzinho.rickandmorty.domain.repository

import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import io.reactivex.rxjava3.core.Single

interface CharacterRepository {

    fun getAll(page: Int): Single<List<CharacterData>>
}