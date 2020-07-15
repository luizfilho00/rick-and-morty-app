package com.mouzinho.rickandmorty.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import com.mouzinho.rickandmorty.data.paging.CharacterDataSource
import com.mouzinho.rickandmorty.domain.interactors.GetCharacters
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel @ViewModelInject constructor(
    private val getCharacters: GetCharacters
) : ViewModel() {

    private val disposables = CompositeDisposable()
    val characters: LiveData<PagedList<CharacterData>> get() = _characters

    private val _characters by lazy {
        object : DataSource.Factory<Int, CharacterData>() {
            override fun create(): DataSource<Int, CharacterData> {
                return CharacterDataSource(getCharacters, disposables)
            }
        }.toLiveData(
            config = PagedList.Config.Builder()
                .setPageSize(5)
                .setEnablePlaceholders(true)
                .build()
        )
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}