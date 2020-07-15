package com.mouzinho.rickandmorty.domain.interactors

import com.mouzinho.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val repository: CharacterRepository
) {

    fun all(page: Int) = repository.getAll(page)
}