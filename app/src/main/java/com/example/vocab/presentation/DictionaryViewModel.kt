package com.example.vocab.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.vocab.data.DictionaryRepositoryImpl
import com.example.vocab.domain.entities.UserWord
import com.example.vocab.domain.use_cases.DeleteUserWordUseCase
import com.example.vocab.domain.use_cases.GetUserDictionaryUseCase
import com.example.vocab.domain.use_cases.GetUserWordsByThematicsUseCase
import kotlinx.coroutines.launch

class DictionaryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DictionaryRepositoryImpl(application)

    private val getUserDictionaryUseCase = GetUserDictionaryUseCase(repository)
    private val getUserWordsByThematicsUseCase = GetUserWordsByThematicsUseCase(repository)
    private val deleteUserWordUseCase = DeleteUserWordUseCase(repository)

    val userDictionary = getUserDictionaryUseCase.getUserDictionary() // LiveData<List<UserWord>>

    fun deleteUserWord(userWord: UserWord){
        viewModelScope.launch {
            deleteUserWordUseCase.deleteUserWord(userWord)
        }
    }

    fun getUserWordsByThematics(thematics : String): LiveData<List<UserWord>> {
        return getUserWordsByThematicsUseCase.getUserWordsByThematics(thematics)
    }



}