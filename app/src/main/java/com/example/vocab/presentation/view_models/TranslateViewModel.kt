package com.example.vocab.presentation.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vocab.data.DictionaryRepositoryImpl
import com.example.vocab.data.api.ApiFactory
import com.example.vocab.data.pojo.PostBody
import com.example.vocab.domain.entities.Word
import com.example.vocab.domain.use_cases.user_dictionary.AddUserWordUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class TranslateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DictionaryRepositoryImpl(application)
    private val addUserWordUseCase = AddUserWordUseCase(repository)
    private val compositeDisposable = CompositeDisposable()
    private val _translatedTextLD = MutableLiveData<String>()
    val translatedTextLD: LiveData<String>
        get() = _translatedTextLD

    fun addUserWord(sourceLanguage: String, sourceText: String, targetText: String) {
        if (sourceLanguage.isNotBlank()) {
            when (sourceLanguage) {
                ENGLISH_INPUT_LANGUAGE -> {
                    val word = Word(
                        value = sourceText,
                        translate = targetText,
                        thematics = NEW_WORDS_THEMATIC
                    )
                    viewModelScope.launch {
                        addUserWordUseCase.addUserWord(word)
                    }
                }
                RUSSIAN_INPUT_LANGUAGE -> {
                    val word = Word(
                        value = targetText,
                        translate = sourceText,
                        thematics = NEW_WORDS_THEMATIC
                    )
                    viewModelScope.launch {
                        addUserWordUseCase.addUserWord(word)
                    }
                }
                else -> throw IllegalArgumentException("Unknown sourceLanguage!")
            }
        }
    }

    fun collectUserDataToPostBody(
        inputTexts: List<String?>,
        targetLanguage: String,
        sourceLanguage: String
    ): PostBody {
        val targetLanguageCode = when (targetLanguage) {
            RUSSIAN_INPUT_LANGUAGE -> RUSSIAN_LANGUAGE
            ENGLISH_INPUT_LANGUAGE -> ENGLISH_LANGUAGE
            else -> throw IllegalArgumentException("Illegal target language !")
        }
        val sourceLanguageCode = when (sourceLanguage) {
            RUSSIAN_INPUT_LANGUAGE -> RUSSIAN_LANGUAGE
            ENGLISH_INPUT_LANGUAGE -> ENGLISH_LANGUAGE
            else -> throw IllegalArgumentException("Illegal source language !")
        }
        if (inputTexts.isNullOrEmpty() || inputTexts[0]?.trim() == "" || inputTexts[0] == null) {
            throw IllegalArgumentException("Input text can't be null or empty !")
        } else {
            return PostBody(
                texts = inputTexts as List<String>,
                targetLanguageCode = targetLanguageCode,
                sourceLanguageCode = sourceLanguageCode
            )
        }
    }

    fun requestToTranslator(postBody: PostBody) {
        viewModelScope.launch {
            val disposable = ApiFactory.apiService.getTranslatedText(postBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _translatedTextLD.postValue(it.translationsList?.get(0)?.text.toString())
                }, {
                    Log.d("LOG", it.message.toString())
                })
            compositeDisposable.addAll(disposable)
        }
    }

    companion object {
        private const val NEW_WORDS_THEMATIC = "Свои слова"
        private const val RUSSIAN_LANGUAGE = "ru"
        private const val ENGLISH_LANGUAGE = "en"
        private const val RUSSIAN_INPUT_LANGUAGE = "Русский"
        private const val ENGLISH_INPUT_LANGUAGE = "Английский"
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}