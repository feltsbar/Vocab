package com.example.vocab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vocab.R
import com.example.vocab.presentation.DictionaryActivity.Companion.newIntendGeneralDictionary
import com.example.vocab.presentation.DictionaryActivity.Companion.newIntendUserDictionary
import com.example.vocab.presentation.adapters.ThematicsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ThematicsAdapter
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUserDictionaryPanel()
        setupRecyclerView()

    }

    private fun setupUserDictionaryPanel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.userDictionary.observe(this) {
            user_dictionary_panel.text = "${it.size} слов всего"
        }
        user_dictionary_panel.setOnClickListener {
            val intent = newIntendUserDictionary(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter = ThematicsAdapter()
        recycler_view_thematics.adapter = adapter
        adapter.thematicList = listOf("irregularVerbs", "SPORT", "FOOD", "NATURE", "CLOTHES")
        adapter.onThematicItemClick = {
            val intent = newIntendGeneralDictionary(this, it)
            startActivity(intent)
        }
    }

}