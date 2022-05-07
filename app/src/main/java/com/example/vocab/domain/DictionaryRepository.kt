package com.example.vocab.domain

import androidx.lifecycle.LiveData
import com.example.vocab.domain.entities.GeneralWord
import com.example.vocab.domain.entities.UserWord

interface DictionaryRepository {

    fun getUserDictionary(): LiveData<List<UserWord>>
    fun getUserWordsByThematics(thematics: String): LiveData<List<UserWord>>
    suspend fun getUserWord(userWordId: Long): UserWord
    suspend fun addUserWord(userWord: UserWord)
    suspend fun deleteUserWord(userWord: UserWord)

    fun getGeneralDictionary(): LiveData<List<GeneralWord>>
    fun getGeneralWordsByThematics(thematics: String): LiveData<List<GeneralWord>>
    suspend fun getGeneralWord(wordId: Long): GeneralWord

}