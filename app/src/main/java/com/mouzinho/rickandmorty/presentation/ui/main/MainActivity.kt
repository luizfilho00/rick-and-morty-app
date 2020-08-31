package com.mouzinho.rickandmorty.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.mouzinho.rickandmorty.databinding.ActivityMainBinding
import com.mouzinho.rickandmorty.presentation.base.ReactiveView
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ReactiveView<CharactersIntent, CharactersState> {
    override val disposables: CompositeDisposable = CompositeDisposable()

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val charactersAdapter by lazy { CharactersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.states().subscribe(::render).let(disposables::add)
        viewModel.processIntents(intents())
    }

    override fun onStop() {
        super.onStop()
        detach()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = charactersAdapter
    }

    override fun render(state: CharactersState) {
        val hasItems = state.characters?.isEmpty() == false
        binding.recyclerView.isVisible = hasItems
        binding.textViewEmpty.isVisible = !hasItems && !state.loading
        binding.progress.isVisible = state.loading
        charactersAdapter.submitList(state.characters)
    }

    override fun intents(): Observable<CharactersIntent> = Observable.merge(
        loadAllIntent(),
        clearAllIntent()
    )

    private fun loadAllIntent() = Observable.just(CharactersIntent.LoadCharacters)

    private fun clearAllIntent() = Observable.just(CharactersIntent.ClearCharacters)

    companion object {
        private const val TAG = "MainActivity"
    }
}