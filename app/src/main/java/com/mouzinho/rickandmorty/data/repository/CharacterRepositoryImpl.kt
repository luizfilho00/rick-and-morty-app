package com.mouzinho.rickandmorty.data.repository

import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import com.mouzinho.rickandmorty.data.service.ApiService
import com.mouzinho.rickandmorty.domain.repository.CharacterRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharacterRepository {

    override fun getAll(page: Int): Single<List<CharacterData>> =
        apiService.getCharacters(page).map { it.results }
}