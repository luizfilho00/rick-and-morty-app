package com.mouzinho.rickandmorty.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import br.com.mouzinho.rxarch.Loading
import br.com.mouzinho.rxarch.RxFragment
import br.com.mouzinho.rxarch.withState
import com.mouzinho.rickandmorty.databinding.FragmentMainBinding
import com.mouzinho.rickandmorty.domain.states.CharacterState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : RxFragment<CharacterState>() {
    override val viewModel: MainViewModel by activityViewModels()

    private var charactersAdapter: CharactersAdapter? = null
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setupRecyclerView()
        binding.buttonAdd.setOnClickListener { viewModel.updateCount() }
        return binding.root
    }

    override fun updateView() = withState(viewModel) { state ->
        binding.loading.isVisible = state.list is Loading
        binding.textViewCount.text = state.count.toString()
        state.list()?.let { charactersAdapter?.submit(lifecycle, it) }
    }

    private fun setupRecyclerView() {
        if (charactersAdapter == null) charactersAdapter = CharactersAdapter()
        binding.recyclerView.adapter = charactersAdapter
    }
}