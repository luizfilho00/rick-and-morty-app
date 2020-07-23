package com.mouzinho.rickandmorty.presentation.ui.main

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import com.airbnb.mvrx.*
import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import com.mouzinho.rickandmorty.data.paging.CharacterDataSource
import com.mouzinho.rickandmorty.domain.interactors.GetCharacters
import org.koin.core.KoinComponent
import org.koin.core.inject

data class CharacterStateX(
    var charData: Async<PagingData<CharacterData>> = Uninitialized
) : MvRxState

class MainXViewModel(
    @PersistState state: CharacterStateX,
    private val getCharacters: GetCharacters
) : MvRxViewModel<CharacterStateX>(state) {

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

    companion object : MvRxViewModelFactory<MainXViewModel, CharacterStateX>, KoinComponent {
        private val getCharacters by inject<GetCharacters>()

        override fun create(viewModelContext: ViewModelContext, state: CharacterStateX): MainXViewModel? {
            return MainXViewModel(state, getCharacters)
        }
    }
}