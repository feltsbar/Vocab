package com.example.vocab.domain

import androidx.lifecycle.LiveData
import com.example.vocab.domain.entities.Word

interface DictionaryRepository {

    fun getUserDictionary(): LiveData<List<Word>>
    suspend fun getUserWordsByThematics(thematics: String): List<Word> // Уберем LiveData
    suspend fun getUserWord(userWordId: Long): Word
    suspend fun addUserWord(word: Word)
    suspend fun deleteUserWord(word: Word)

    fun getGeneralDictionary(): LiveData<List<Word>>
    suspend fun getGeneralWordsByThematics(thematics: String): List<Word>
    suspend fun getGeneralWord(wordId: Long): Word

}