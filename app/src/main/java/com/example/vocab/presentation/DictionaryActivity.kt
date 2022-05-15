package com.example.vocab.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vocab.R
import com.example.vocab.presentation.adapters.UserDictionaryAdapter
import kotlinx.android.synthetic.main.activity_dictionary.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DictionaryActivity : AppCompatActivity() {

    private lateinit var viewModel: DictionaryViewModel
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        viewModel = ViewModelProvider(this)[DictionaryViewModel::class.java]
        setupRecyclerView()
        parseIntent()
    }

    private fun setupRecyclerView(thematics: String = "") {
        val adapter = UserDictionaryAdapter()
        recycler_view.adapter = adapter
        if (thematics == "") {
            val extraContent = intent.getStringExtra(EXTRA_CONTENT).toString()
            title = extraContent
            viewModel.userDictionary.observe(this) {
                adapter.dictionaryList = it
            }
        } else {
            val extraThematic = intent.getStringExtra(EXTRA_CONTENT).toString()
            title = extraThematic
            scope.launch {
                adapter.dictionaryList = viewModel.getGeneralWordsByThematics(extraThematic)
            }
            adapter.onWordItemLongClick = {
                viewModel.addUserWord(it)
                Toast.makeText(
                    this@DictionaryActivity,
                    "Слово добавлено в Мой словарь!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun parseIntent() {
        if (intent.hasExtra(DICTIONARY_MODE)) {
            when {
                intent.getStringExtra(DICTIONARY_MODE) == USER_MODE -> {
                    setupRecyclerView()
                }
                intent.getStringExtra(DICTIONARY_MODE) == GENERAL_MODE -> {
                    setupRecyclerView(intent.getStringExtra(EXTRA_CONTENT).toString())
                }
                else -> throw RuntimeException("Unknown DICTIONARY_MODE !")
            }
        } else throw RuntimeException("intent has not Extra !")
    }

    companion object {
        private const val DICTIONARY_MODE = "dictionaryMode"
        private const val USER_MODE = "userMode"
        private const val GENERAL_MODE = "generalMode"
        private const val EXTRA_CONTENT = "extraContent"
        private const val USER_DICTIONARY = "Мой словарь"

        fun newIntendUserDictionary(context: Context): Intent {
            val intent = Intent(context, DictionaryActivity::class.java)
            intent.putExtra(DICTIONARY_MODE, USER_MODE)
            intent.putExtra(EXTRA_CONTENT, USER_DICTIONARY)
            return intent
        }

        fun newIntendGeneralDictionary(context: Context, thematics: String): Intent {
            val intent = Intent(context, DictionaryActivity::class.java)
            intent.putExtra(DICTIONARY_MODE, GENERAL_MODE)
            intent.putExtra(EXTRA_CONTENT, thematics)
            return intent
        }
    }

}