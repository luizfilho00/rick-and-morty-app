package com.mouzinho.rickandmorty.presentation.ui.main

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.observable
import com.airbnb.mvrx.*
import com.mouzinho.rickandmorty.data.paging.CharacterDataSource
import com.mouzinho.rickandmorty.domain.interactors.GetCharacters
import com.mouzinho.rickandmorty.domain.states.CharacterState
import com.mouzinho.rickandmorty.presentation.ui.base.MvRxViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel(
    @PersistState state: CharacterState,
    private val getCharacters: GetCharacters
) : MvRxViewModel<CharacterState>(state) {

    init {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { CharacterDataSource(getCharacters) }
        )
        pager.observable
            .execute { copy(charData = it) }
    }

    companion object : MvRxViewModelFactory<MainViewModel, CharacterState>, KoinComponent {
        private val getCharacters by inject<GetCharacters>()

        override fun create(viewModelContext: ViewModelContext, state: CharacterState): MainViewModel? {
            return MainViewModel(state, getCharacters)
        }
    }
}