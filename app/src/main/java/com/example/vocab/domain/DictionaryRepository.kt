package com.example.vocab.domain

import androidx.lifecycle.LiveData
import com.example.vocab.domain.entities.Word

interface DictionaryRepository {

    fun getUserDictionary(): LiveData<List<Word>>
    fun getUserWordsByThematics(thematics: String): LiveData<List<Word>>
    suspend fun getUserWord(userWordId: Long): Word
    suspend fun addUserWord(word: Word)
    suspend fun deleteUserWord(word: Word)

    fun getGeneralDictionary(): LiveData<List<Word>>
    fun getGeneralWordsByThematics(thematics: String): LiveData<List<Word>>
    suspend fun getGeneralWord(wordId: Long): Word

}