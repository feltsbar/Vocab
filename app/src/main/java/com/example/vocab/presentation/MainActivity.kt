package com.example.vocab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vocab.R
import com.example.vocab.presentation.DictionaryActivity.Companion.newIntendUserDictionary
import com.example.vocab.presentation.adapters.ThematicsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var adapter: ThematicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        user_dictionary_panel.setOnClickListener {
            val intent = newIntendUserDictionary(this)
            startActivity(intent)
        }
        viewModel.userDictionary.observe(this) {
            user_dictionary_panel.text = "${it.size} слов всего"
        }
    }

    private fun setupRecyclerView() {
        adapter = ThematicsAdapter()
        recycler_view_thematics.adapter = adapter
        adapter.thematicList = listOf("USER_WORDS", "IRREGULAR_VERBS", "SPORT", "FOOD", "NATURE")
    }

}