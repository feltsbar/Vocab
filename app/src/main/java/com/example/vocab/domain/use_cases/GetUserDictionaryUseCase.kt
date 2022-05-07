package com.example.vocab.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.UserWord

class GetUserDictionaryUseCase(private val dictionaryRepository: DictionaryRepository) {

    fun getUserDictionary(): LiveData<List<UserWord>> {
        return dictionaryRepository.getUserDictionary()
    }
}