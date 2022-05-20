package com.example.vocab.presentation.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vocab.R
import com.example.vocab.data.api.ApiFactory
import io.reactivex.disposables.CompositeDisposable

class TranslateActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

    }




    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {
        fun newIntendTranslate(context: Context): Intent {
            val intent = Intent(context, TranslateActivity::class.java)
//            intent.putExtra(DICTIONARY_MODE, USER_MODE)
//            intent.putExtra(EXTRA_CONTENT, USER_DICTIONARY)
            return intent
        }
    }
}