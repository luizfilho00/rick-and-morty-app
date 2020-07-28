package com.mouzinho.rickandmorty.domain.states

import androidx.paging.PagingData
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.mouzinho.rickandmorty.data.entity.response.CharacterData

data class CharacterState(
    var charData: Async<PagingData<CharacterData>> = Uninitialized
) : MvRxState