package com.mouzinho.rickandmorty.data.paging

import androidx.paging.rxjava2.RxPagingSource
import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import com.mouzinho.rickandmorty.data.service.ApiService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CharacterDataSource(
    private val apiService: ApiService
) : RxPagingSource<Int, CharacterData>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CharacterData>> {
        return apiService.getCharacters(params.key ?: 1)
            .subscribeOn(Schedulers.io())
            .map {
                LoadResult.Page(
                    it.results,
                    it.info.prev?.substringAfter("=")?.toInt(),
                    it.info.next?.substringAfter("=")?.toInt()
                )
            }
    }
}