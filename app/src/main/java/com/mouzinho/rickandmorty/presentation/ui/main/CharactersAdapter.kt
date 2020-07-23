package com.mouzinho.rickandmorty.presentation.ui.main

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mouzinho.rickandmorty.data.entity.response.CharacterData

class CharactersAdapter : PagingDataAdapter<CharacterData, CharacterViewHolder>(
    object : DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem == newItem
        }

    }
) {
    private var hasSubmittedOnce = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * This is needed because Paging 3 is crashing on configuration changes
     * Crash is: java.lang.IllegalStateException: Attempt to collect twice from pageEventFlow, which is an illegal operation.
     */
    fun submit(lifecycle: Lifecycle, pagingData: PagingData<CharacterData>) {
        if (hasSubmittedOnce) return
        submitData(lifecycle, pagingData)
        hasSubmittedOnce = true
    }
}