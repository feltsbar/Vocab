package com.example.vocab.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.vocab.data.DictionaryRepositoryImpl
import com.example.vocab.domain.entities.Word
import com.example.vocab.domain.use_cases.*
import kotlinx.coroutines.launch

class DictionaryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DictionaryRepositoryImpl(application)

    private val getUserDictionaryUseCase = GetUserDictionaryUseCase(repository)
    private val getUserWordsByThematicsUseCase = GetUserWordsByThematicsUseCase(repository)
    private val deleteUserWordUseCase = DeleteUserWordUseCase(repository)
    private val getUserWordUseCase = GetUserWordUseCase(repository)
    private val addUserWordUseCase = AddUserWordUseCase(repository)
    private val getGeneralDictionaryUseCase = GetGeneralDictionaryUseCase(repository)
    private val getGeneralWordsByThematicsUseCase = GetGeneralWordsByThematicsUseCase(repository)
    private val getGeneralWordUseCase = GetGeneralWordUseCase(repository)

    val userDictionary = getUserDictionaryUseCase.getUserDictionary() // LiveData<List<UserWord>>

//    init {
//        deleteUserWord(4)
//        deleteUserWord(1)
//    }

    fun deleteUserWord(wordId: Long){
        viewModelScope.launch {
            deleteUserWordUseCase.deleteUserWord(wordId)
        }
    }

    suspend fun getUserWordsByThematics(thematics : String): List<Word> {
        return getUserWordsByThematicsUseCase.getUserWordsByThematics(thematics)
    }

}