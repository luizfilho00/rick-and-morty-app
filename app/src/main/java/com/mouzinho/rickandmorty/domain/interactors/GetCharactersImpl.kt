package com.mouzinho.rickandmorty.domain.interactors

import com.mouzinho.rickandmorty.domain.repository.CharacterRepository
import com.mouzinho.rickandmorty.presentation.ui.main.CharactersState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetCharactersImpl @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(page: Int): Observable<CharactersState> {
        return Observable
            .just(CharactersState.idle())
            .mergeWith(
                repository.getFromPage(page)
                    .map { CharactersState(it.results, false, null) }
                    .onErrorReturn {
                        CharactersState(
                            emptyList(),
                            false,
                            Exception("Qq houve será?")
                        )
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .toObservable()
            )
    }
}