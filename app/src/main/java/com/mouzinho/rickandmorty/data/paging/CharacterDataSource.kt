package com.mouzinho.rickandmorty.data.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import com.mouzinho.rickandmorty.domain.interactors.GetCharacters
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CharacterDataSource(
    private val getCharacters: GetCharacters,
    private val disposables: CompositeDisposable
) : PageKeyedDataSource<Int, CharacterData>() {

    private var lastPageLoaded = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterData>
    ) {
        getCharacters.all(++lastPageLoaded)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    callback.onResult(
                        it,
                        null,
                        lastPageLoaded + 1
                    )
                },
                { Log.d("DataSource", "loadInitial: $it") }
            )
            .let(disposables::add)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterData>) {
        getCharacters.all(params.key)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    callback.onResult(it, if (params.key > 1) params.key - 1 else null)
                },
                { Log.d("DataSource", "loadBefore: $it") }
            )
            .let(disposables::add)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterData>) {
        getCharacters.all(params.key)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    callback.onResult(it, if (params.key > 1) params.key + 1 else null)
                },
                { Log.d("DataSource", "loadAfter: $it") }
            )
            .let(disposables::add)
    }
}