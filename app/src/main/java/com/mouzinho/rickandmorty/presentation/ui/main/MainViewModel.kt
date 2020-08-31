package com.mouzinho.rickandmorty.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.mouzinho.rickandmorty.domain.interactors.GetCharactersImpl
import com.mouzinho.rickandmorty.presentation.base.BaseViewModel
import com.mouzinho.rickandmorty.presentation.ui.main.CharactersIntent.LoadCharacters
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject

class MainViewModel @ViewModelInject constructor(
    private val getCharacters: GetCharactersImpl,
    private val charactersStore: CharactersStore
) : ViewModel(), BaseViewModel<CharactersIntent, CharactersState> {

    private val intentsSubject: PublishSubject<CharactersIntent> = PublishSubject.create()
    private val statesObservable: Observable<CharactersState> = compose()

    private val processIntents
        get() =
            ObservableTransformer<CharactersIntent, CharactersState> { actions ->
                actions.publish { shared ->
                    shared.ofType(LoadCharacters::class.java).compose { getCharacters(0) }
                }
            }
    private val intentFilter: ObservableTransformer<CharactersIntent, CharactersIntent>
        get() =
            ObservableTransformer { intents ->
                intents.publish { shared ->
                    Observable.merge(
                        shared.ofType(LoadCharacters::class.java).take(1),
                        shared.filter { !(LoadCharacters::class.java).isInstance(it) }
                    )
                }
            }

    override fun processIntents(intent: Observable<CharactersIntent>) {
        intent.subscribe(intentsSubject)
    }

    override fun states(): Observable<CharactersState> = statesObservable

    private fun compose(): Observable<CharactersState> = intentsSubject
        .compose(intentFilter)
        .compose(processIntents)
        .scan(CharactersState.idle(), charactersStore.reduce)
        .distinctUntilChanged()
        .replay(1)
        .autoConnect(0)
}