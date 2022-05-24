package com.example.vocab.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vocab.data.DictionaryRepositoryImpl
import com.example.vocab.data.pojo.PostBody
import com.example.vocab.domain.use_cases.AddUserWordUseCase

class TranslateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DictionaryRepositoryImpl(application)
    private val addUserWordUseCase = AddUserWordUseCase(repository)


    // Метод собирает введенный пользователем текст и проверяет языки с какого на какой переводить
    // и сам введеный текст, для дальнейшего возврата готовго тела для запроса
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

    companion object {
        private const val RUSSIAN_LANGUAGE = "ru"
        private const val ENGLISH_LANGUAGE = "en"
        private const val RUSSIAN_INPUT_LANGUAGE = "Русский"
        private const val ENGLISH_INPUT_LANGUAGE = "Английский"
    }
}