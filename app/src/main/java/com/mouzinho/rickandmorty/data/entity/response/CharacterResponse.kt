package com.mouzinho.rickandmorty.data.entity.response

data class CharacterResponse(
    val info: Info?,
    val results: List<CharacterData>?
)