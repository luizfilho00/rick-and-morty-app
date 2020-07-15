package com.mouzinho.rickandmorty.domain.interactors

import com.mouzinho.rickandmorty.data.entity.response.CharacterResponse
import io.reactivex.Single

interface GetCharacters {
    fun execute(page: Int): Single<CharacterResponse>
}