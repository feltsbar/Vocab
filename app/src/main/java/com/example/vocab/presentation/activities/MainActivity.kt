package com.example.vocab.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.vocab.R
import com.example.vocab.presentation.activities.DictionaryActivity.Companion.newIntendGeneralDictionary
import com.example.vocab.presentation.activities.DictionaryActivity.Companion.newIntendUserDictionary
import com.example.vocab.presentation.activities.TranslateActivity.Companion.newIntendTranslate
import com.example.vocab.presentation.view_models.MainViewModel
import com.example.vocab.presentation.adapters.ThematicsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ThematicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUserDictionaryPanel()
        setupRecyclerView()
        button_open_translator.setOnClickListener {
            val intent = newIntendTranslate(this)
            startActivity(intent)
        }
    }

    private fun setupUserDictionaryPanel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.userDictionary.observe(this) {
            user_dictionary_panel.text = "${it.size} слов всего"
        }
        viewModel.generalDictionary.observe(this) {
            if (it.isEmpty()) lifecycleScope.launch {
                viewModel.fillGeneralDictionary()
            }
        }
        card_user_dictionary_panel.setOnClickListener {
            val intent = newIntendUserDictionary(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter = ThematicsAdapter()
        recycler_view_thematics.adapter = adapter
        val listOfThematics = listOf(
            "Топ 100 слов",
            "Неправильные глаголы",
            "IT-термины",
            "Для путешествий",
            "Для литератора")
        adapter.thematicList = listOfThematics
        adapter.onThematicItemClick = {
            val intent = newIntendGeneralDictionary(this, it)
            startActivity(intent)
        }
    }

}