package com.mouzinho.rickandmorty.data.paging

import androidx.paging.rxjava2.RxPagingSource
import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import com.mouzinho.rickandmorty.data.entity.response.CharacterResponse
import com.mouzinho.rickandmorty.domain.interactors.GetCharacters
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CharacterDataSource(
    private val getCharacters: GetCharacters
) : RxPagingSource<Int, CharacterData>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CharacterData>> {
        return getCharacters.execute(params.key ?: 1)
            .subscribeOn(Schedulers.io())
            .onErrorReturn {
                CharacterResponse(null, null)
            }
            .map {
                LoadResult.Page(
                    it.results ?: emptyList(),
                    it.info?.prev?.substringAfter("=")?.toInt(),
                    it.info?.next?.substringAfter("=")?.toInt()
                )
            }
    }
}