package com.mouzinho.rickandmorty.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mouzinho.rickandmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private val charactersAdapter by lazy { CharactersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        subscribeUi()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = charactersAdapter
    }

    private fun subscribeUi() {
        viewModel.characters.observe(this, Observer { pagingData ->
            charactersAdapter.submitData(lifecycle, pagingData)
        })
    }
}