package com.example.vocab.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.vocab.domain.entities.Word

class WordItemDiffCallback : DiffUtil.ItemCallback<Word>() {

    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }
}