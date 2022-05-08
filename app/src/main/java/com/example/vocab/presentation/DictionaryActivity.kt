package com.example.vocab.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.vocab.R

class DictionaryActivity : AppCompatActivity() {

    private lateinit var viewModel: DictionaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        viewModel = ViewModelProvider(this)[DictionaryViewModel::class.java]
        viewModel.userDictionary.observe(this){
            Log.d("UserDictionary", it.toString())
        }
    }

    companion object {
        private const val USER_MODE = "userMode"
        private const val GENERAL_MODE = "generalMode"
        private const val USER_DICTIONARY = "userDict"
        private const val IRREGULAR_VERBS = "irregularVerbs"

        fun newIntendUserDictionary(context: Context): Intent {
            val intent = Intent(context, DictionaryActivity::class.java)
            intent.putExtra(USER_MODE, USER_DICTIONARY)
            return intent
        }

        fun newIntendGeneralDictionary(context: Context): Intent {
            val intent = Intent(context, DictionaryActivity::class.java)
            intent.putExtra(GENERAL_MODE, IRREGULAR_VERBS)
            return intent
        }
    }

}