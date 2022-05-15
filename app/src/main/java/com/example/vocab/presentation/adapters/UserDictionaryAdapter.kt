package com.example.vocab.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vocab.R
import com.example.vocab.domain.entities.Word
import kotlinx.android.synthetic.main.item_word.view.*

class UserDictionaryAdapter : RecyclerView.Adapter<UserDictionaryAdapter.UserWordViewHolder>() {

    var dictionaryList = listOf<Word>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onWordItemLongClick: ((Word) -> Unit)? = null

    inner class UserWordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val value = view.tv_value
        val translate = view.tv_translate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserWordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_word,
            parent,
            false
        )
        return UserWordViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserWordViewHolder, position: Int) {
        val word = dictionaryList[position]
        holder.value.text = word.value
        holder.translate.text = word.translate
        holder.itemView.setOnLongClickListener {
            onWordItemLongClick?.invoke(word)
            true
        }
    }

    override fun getItemCount() = dictionaryList.size
}