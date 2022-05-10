package com.example.vocab.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vocab.R
import com.example.vocab.domain.entities.UserWord
import kotlinx.android.synthetic.main.item_word.view.*

class UserDictionaryAdapter : RecyclerView.Adapter<UserDictionaryAdapter.UserWordViewHolder>() {

    var userDictionaryList = listOf<UserWord>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
        val word = userDictionaryList[position]
        holder.value.text = word.value
        holder.translate.text = word.translate
    }

    override fun getItemCount() = userDictionaryList.size
}