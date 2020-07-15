package com.mouzinho.rickandmorty.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mouzinho.rickandmorty.R
import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import com.mouzinho.rickandmorty.databinding.ItemCharacterBinding

class CharacterViewHolder private constructor(
    private val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(characterData: CharacterData?) {
        binding.textView.text = characterData?.name
        with(binding.imageView) {
            Glide.with(this)
                .load(characterData?.image)
                .circleCrop()
                .into(this)
        }
    }

    companion object {
        fun inflate(parent: ViewGroup) =
            CharacterViewHolder(
                ItemCharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}