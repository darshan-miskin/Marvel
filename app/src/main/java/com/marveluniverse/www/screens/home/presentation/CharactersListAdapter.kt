package com.marveluniverse.www.screens.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.marveluniverse.www.R
import com.marveluniverse.www.databinding.ItemRecyclerCharactersBinding
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModelDiffUtil

class CharactersListAdapter(
    private val listener: Listener,
    private val lifecycleOwner: LifecycleOwner) :
    PagingDataAdapter<CharacterModel, CharactersListAdapter.CharacterHolder>(CharacterModelDiffUtil()) {

    interface Listener {
        fun onCharacterClick(
            characterModel: CharacterModel,
            imageView: ImageView,
            textView: TextView
        )
    }

    inner class CharacterHolder(val binding: ItemRecyclerCharactersBinding) : ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener { _ ->
                getItem(absoluteAdapterPosition)?.let {
                    listener.onCharacterClick(
                        it,
                        binding.ivThumbnail,
                        binding.txtCharacterName
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = DataBindingUtil.inflate<ItemRecyclerCharactersBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_recycler_characters,
            parent,
            false)
        binding.lifecycleOwner = lifecycleOwner
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = getItem(position)
        holder.binding.character = character
    }
}