package com.mouzinho.rickandmorty.data.repository

import com.mouzinho.rickandmorty.data.service.ApiService
import com.mouzinho.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharacterRepository {

    override fun getFromPage(page: Int) = apiService.getCharacters(page)
}