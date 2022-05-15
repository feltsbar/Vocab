package com.example.vocab.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocab.data.DictionaryRepositoryImpl
import com.example.vocab.domain.entities.Word
import com.example.vocab.domain.use_cases.AddGeneralWordUseCase
import com.example.vocab.domain.use_cases.GetGeneralDictionaryUseCase
import com.example.vocab.domain.use_cases.GetUserDictionaryUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DictionaryRepositoryImpl(application)
    private val getGeneralDictionaryUseCase = GetGeneralDictionaryUseCase(repository)
    private val getUserDictionaryUseCase = GetUserDictionaryUseCase(repository)
    private val addGeneralWordUseCase = AddGeneralWordUseCase(repository)

    val userDictionary = getUserDictionaryUseCase.getUserDictionary() // LiveData<List<Word>>

//    init {
//        viewModelScope.launch {
//            addGeneralWordUseCase.addGeneralWord(Word(
//                value = "hello",
//                translate = "Привет",
//                thematics = "greeting"
//            )
//            )
//        }
//    }

    suspend fun addGeneralWord(word: Word) {
        viewModelScope.launch {
            addGeneralWordUseCase.addGeneralWord(word)
        }
    }

//    fun getGeneralThematics(): List<String> {
//        MediatorLiveData<List<Word>>().apply {
//            addSource(getGeneralDictionaryUseCase.getGeneralDictionary()) {
//                val resultMap = mutableMapOf<String, MutableList<Word>>()
//                for (word in it) {
//                    if (resultMap.containsKey(word.thematics)) {
//                        resultMap[word.thematics]?.add(word)
//                    } else {
//                        resultMap[word.thematics] = mutableListOf(word)
//                    }
//                }
//                value = resultMap.keys.toList()
//            }
//        }
//    }


}