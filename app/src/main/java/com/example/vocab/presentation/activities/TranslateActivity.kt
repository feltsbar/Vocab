package com.example.vocab.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vocab.R
import com.example.vocab.presentation.view_models.TranslateViewModel
import kotlinx.android.synthetic.main.activity_translate.*

class TranslateActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory)[TranslateViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        button_translate.setOnClickListener {
            viewModel.requestToTranslator(
                viewModel.collectUserDataToPostBody(
                    listOf(et_text_to_translate.text?.toString()),
                    tv_target_language.text.toString(),
                    tv_source_language.text.toString()
                )
            )
            viewModel.translatedTextLD.observe(this) {
                tv_translated_text.text = it
            }
        }

        button_change_language.setOnClickListener {
            changeTranslatingLanguage()
        }
    }

    // Метод меняет местами языки с какого на какой будет производиться перевод
    private fun changeTranslatingLanguage() {
        val mediatorLanguage = tv_source_language.text.toString()
        tv_source_language.text = tv_target_language.text.toString()
        tv_target_language.text = mediatorLanguage
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    companion object {
        fun newIntendTranslate(context: Context): Intent {
            val intent = Intent(context, TranslateActivity::class.java)
            return intent
        }
    }

}