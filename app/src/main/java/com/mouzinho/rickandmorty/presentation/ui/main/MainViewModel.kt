package com.mouzinho.rickandmorty.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mouzinho.rickandmorty.data.paging.CharacterDataSource
import com.mouzinho.rickandmorty.data.service.ApiService
import io.reactivex.disposables.CompositeDisposable

class MainViewModel @ViewModelInject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val characters by lazy {
        Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { CharacterDataSource(apiService) }
        ).liveData
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}