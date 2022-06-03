package com.example.vocab.domain.use_cases.general_dictionary

import androidx.lifecycle.LiveData
import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class GetGeneralDictionaryUseCase(private val dictionaryRepository: DictionaryRepository) {

    fun getGeneralDictionary(): LiveData<List<Word>> {
        return dictionaryRepository.getGeneralDictionary()
    }
}