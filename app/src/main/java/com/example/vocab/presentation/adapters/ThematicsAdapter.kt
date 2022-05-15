package com.example.vocab.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vocab.R

class ThematicsAdapter : RecyclerView.Adapter<ThematicsAdapter.ThematicsViewHolder>() {
    class ThematicsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamePanel = view.findViewById<TextView>(R.id.text_view_theme)
        val tvCountWords = view.findViewById<TextView>(R.id.text_view_count_of_words)
    }

    var thematicList = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThematicsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.thematic_panel,
            parent,
            false
        )
        return ThematicsViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ThematicsViewHolder, position: Int) {
        val thematicItem = thematicList[position]
        when(thematicItem) {
            "IRREGULAR_VERBS" -> {

            }
        }
        viewHolder.tvNamePanel.text = thematicItem
        viewHolder.itemView.setOnClickListener {
            Log.d("ThematicClick", "Clicked on $thematicItem theme")
        }
    }

    override fun getItemCount(): Int {
        return thematicList.size
    }
}