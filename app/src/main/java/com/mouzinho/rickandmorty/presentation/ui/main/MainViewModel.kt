package com.mouzinho.rickandmorty.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.observable
import br.com.mouzinho.rxarch.RxViewModel
import com.mouzinho.rickandmorty.data.paging.CharacterDataSource
import com.mouzinho.rickandmorty.domain.interactors.GetCharacters
import com.mouzinho.rickandmorty.domain.states.CharacterState

class MainViewModel @ViewModelInject constructor(
    state: CharacterState,
    private val getCharacters: GetCharacters
) : RxViewModel<CharacterState>(state) {

    init {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { CharacterDataSource(getCharacters) }
        )
        pager.observable
            .execute { copy(list = it) }
    }

    fun updateCount() = updateState { copy(count = ++count) }
}