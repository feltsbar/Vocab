package com.example.vocab.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vocab.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        card_view_user_dictionary.setOnClickListener {

        }
    }
}