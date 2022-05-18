package com.example.vocab.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vocab.R
import com.example.vocab.domain.entities.Word
import com.example.vocab.presentation.WordItemDiffCallback
import kotlinx.android.synthetic.main.item_word.view.*

class DictionaryAdapter :
    ListAdapter<Word, DictionaryAdapter.UserWordViewHolder>(WordItemDiffCallback()) {

    var onWordItemLongClick: ((Word) -> Unit)? = null
    var countBind = 0
    var countCreate = 0

    inner class UserWordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val value = view.tv_value
        val translate = view.tv_translate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserWordViewHolder {
        Log.d("ViewHolder", "onCreateViewHolder : ${++countCreate}")
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_word,
            parent,
            false
        )
        return UserWordViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserWordViewHolder, position: Int) {
        val word = getItem(position)
        holder.value.text = word.value
        holder.translate.text = word.translate
        holder.itemView.setOnLongClickListener {
            onWordItemLongClick?.invoke(word)
            true
        }
    }

}