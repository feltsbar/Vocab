package com.example.vocab.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.vocab.R
import com.example.vocab.data.api.ApiFactory
import com.example.vocab.data.pojo.PostBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TranslateActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        // todo вызвать метод requestToTranslator() и задать в параметры значения из разметки


    }

    private fun requestToTranslator(
        inputTexts : List<String>,
        targetLanguage : String,
        sourceLanguage : String
    ) : String {

        var result = ""
        val body = PostBody(
            texts = inputTexts,
            targetLanguageCode = targetLanguage,
            sourceLanguageCode = sourceLanguage
        )
        val disposable = ApiFactory.apiService.getTranslatedText(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result = it.toString()
                Log.d("LOG", it.toString())
            }, {
                Log.d("LOG", it.message.toString())
            })
        compositeDisposable.addAll(disposable)
        return result
    }

    companion object {
        fun newIntendTranslate(context: Context): Intent {
            val intent = Intent(context, TranslateActivity::class.java)
            return intent
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}