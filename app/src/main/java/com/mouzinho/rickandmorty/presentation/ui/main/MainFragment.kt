package com.mouzinho.rickandmorty.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.mouzinho.rickandmorty.databinding.FragmentMainBinding

class MainFragment : BaseMvRxFragment() {
    private val viewModel: MainViewModel by fragmentViewModel()

    private var charactersAdapter: CharactersAdapter? = null
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun invalidate() = withState(viewModel) { state ->
        state.charData()?.let { charactersAdapter?.submit(lifecycle, it) }
        Unit
    }

    private fun setupRecyclerView() {
        if (charactersAdapter == null) charactersAdapter = CharactersAdapter()
        binding.recyclerView.adapter = charactersAdapter
    }
}