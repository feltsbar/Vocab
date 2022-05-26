package com.example.vocab.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
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

        button_add_to_user_dictionary.isEnabled = false
        title = intent.getStringExtra(ACTIVITY_NAME)
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
                button_add_to_user_dictionary.isEnabled = !tv_translated_text.text.isNullOrBlank()
            }
        }
        button_change_language.setOnClickListener {
            changeTranslatingLanguage()
            button_add_to_user_dictionary.isEnabled = false
        }
        button_add_to_user_dictionary.setOnClickListener {
            addTranslatedWordIntoDictionary()
        }
    }

    private fun addTranslatedWordIntoDictionary() {
        if (tv_translated_text.text.isNullOrBlank() || et_text_to_translate.text.isNullOrBlank()) {
            Toast.makeText(
                this,
                "Необходимо перевести слово или текст, чтобы его добавить в словарь!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            viewModel.addUserWord(
                tv_source_language.text.toString(),
                et_text_to_translate.text.toString(),
                tv_translated_text.text.toString()
            )
            Toast.makeText(
                this,
                "Слово добавлено в Мой словарь!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

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
        private const val TRANSLATE_ACTIVITY = "Переводчик"
        private const val ACTIVITY_NAME = "activity_name"

        fun newIntendTranslate(context: Context): Intent {
            val intent = Intent(context, TranslateActivity::class.java)
            intent.putExtra(ACTIVITY_NAME, TRANSLATE_ACTIVITY)
            return intent
        }
    }

}