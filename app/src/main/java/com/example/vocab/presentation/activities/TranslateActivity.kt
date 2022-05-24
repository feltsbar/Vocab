package com.example.vocab.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vocab.R
import com.example.vocab.data.api.ApiFactory
import com.example.vocab.data.pojo.PostBody
import com.example.vocab.presentation.view_models.TranslateViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_translate.*

class TranslateActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        )[TranslateViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        // Переопределение функции срабатываемой при на Enter экранной клавиатуры
        et_text_to_translate.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                tv_translated_text.text = requestToTranslator(
                    viewModel.collectUserDataToPostBody(
                        listOf(et_text_to_translate.text?.toString()),
                        tv_target_language.text.toString(),
                        tv_source_language.text.toString()
                    )
                )
                return@OnKeyListener true
            }
            false
        })

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

    private fun requestToTranslator(postBody: PostBody): String {
        var resultOfTranslate = ""
        val disposable = ApiFactory.apiService.getTranslatedText(postBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                resultOfTranslate = it.translationsList?.get(0)?.text.toString()
                Log.d("LOG", resultOfTranslate)
            }, {
                Log.d("LOG", it.message.toString())
            })
        compositeDisposable.addAll(disposable)
        return resultOfTranslate
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {
        fun newIntendTranslate(context: Context): Intent {
            val intent = Intent(context, TranslateActivity::class.java)
            return intent
        }
    }

}