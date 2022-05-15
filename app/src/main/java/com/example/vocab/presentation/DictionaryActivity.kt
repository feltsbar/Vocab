package com.example.vocab.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vocab.R
import com.example.vocab.presentation.adapters.UserDictionaryAdapter
import kotlinx.android.synthetic.main.activity_dictionary.*

class DictionaryActivity : AppCompatActivity() {

    private lateinit var viewModel: DictionaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        val adapter = UserDictionaryAdapter()
        recycler_view.adapter = adapter
        viewModel = ViewModelProvider(this)[DictionaryViewModel::class.java]
        viewModel.generalDictionary.observe(this) {
            adapter.dictionaryList = it
            // Log.d("UserDictionary", it.toString())
        }
    }

    companion object {
        private const val USER_MODE = "userMode"
        private const val GENERAL_MODE = "generalMode"
        private const val USER_DICTIONARY = "Мой словарь"
        private const val IRREGULAR_VERBS = "Неправильные глаголы"

        fun newIntendUserDictionary(context: Context): Intent {
            val intent = Intent(context, DictionaryActivity::class.java)
            intent.putExtra(USER_MODE, USER_DICTIONARY)
            return intent
        }

        fun newIntendGeneralDictionary(context: Context, thematics : String): Intent {
            val intent = Intent(context, DictionaryActivity::class.java)
            intent.putExtra(GENERAL_MODE, thematics)
            return intent
        }
    }

}