package com.mouzinho.rickandmorty.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mouzinho.rickandmorty.data.entity.response.CharacterData
import com.mouzinho.rickandmorty.databinding.ItemCharacterBinding

class CharacterViewHolder private constructor(
    private val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(characterData: CharacterData?) {
        binding.textViewName.text = characterData?.name
        binding.textViewOrigin.text = characterData?.origin?.name
        binding.textViewLastLocation.text = characterData?.location?.name
        with(binding.imageView) {
            Glide.with(this)
                .load(characterData?.image)
                .transform(RoundedCorners(8))
                .centerCrop()
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