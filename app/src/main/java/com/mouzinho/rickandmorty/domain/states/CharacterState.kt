package com.mouzinho.rickandmorty.domain.states

import androidx.paging.PagingData
import br.com.mouzinho.rxarch.Async
import br.com.mouzinho.rxarch.Empty
import br.com.mouzinho.rxarch.RxState
import com.mouzinho.rickandmorty.data.entity.response.CharacterData

data class CharacterState(
    val list: Async<PagingData<CharacterData>> = Empty,
    var count: Int = 0
) : RxState