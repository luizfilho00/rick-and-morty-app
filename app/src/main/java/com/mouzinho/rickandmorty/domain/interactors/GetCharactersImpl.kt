package com.mouzinho.rickandmorty.domain.interactors

import com.mouzinho.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersImpl @Inject constructor(
    private val repository: CharacterRepository
) : GetCharacters {

    override fun execute(page: Int) = repository.getFromPage(page)
}