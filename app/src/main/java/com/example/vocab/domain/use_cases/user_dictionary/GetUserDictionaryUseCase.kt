package com.example.vocab.domain.use_cases.user_dictionary

import androidx.lifecycle.LiveData
import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class GetUserDictionaryUseCase(private val dictionaryRepository: DictionaryRepository) {

    fun getUserDictionary(): LiveData<List<Word>> {
        return dictionaryRepository.getUserDictionary()
    }
}