package com.example.vocab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vocab.R
import com.example.vocab.presentation.DictionaryActivity.Companion.newIntendUserDictionary
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        card_view_user_dictionary.setOnClickListener {
            val intent = newIntendUserDictionary(this)
            startActivity(intent)
        }
    }


}