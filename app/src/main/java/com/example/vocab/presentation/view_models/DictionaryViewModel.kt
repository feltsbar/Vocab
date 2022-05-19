package com.example.vocab.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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
    val generalDictionary = getGeneralDictionaryUseCase.getGeneralDictionary()

    fun addUserWord(word: Word) {
        viewModelScope.launch {
            addUserWordUseCase.addUserWord(word)
        }
    }

    fun deleteUserWord(wordId: Long) {
        viewModelScope.launch {
            deleteUserWordUseCase.deleteUserWord(wordId)
        }
    }

    suspend fun getUserWordsByThematics(thematics: String): List<Word> {
        return getUserWordsByThematicsUseCase.getUserWordsByThematics(thematics)
    }

    suspend fun getGeneralWordsByThematics(thematics: String): List<Word> {
        return getGeneralWordsByThematicsUseCase.getGeneralWordsByThematics(thematics)
    }

}