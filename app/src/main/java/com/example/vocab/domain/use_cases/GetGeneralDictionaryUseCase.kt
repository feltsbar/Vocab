package com.example.vocab.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.GeneralWord

class GetGeneralDictionaryUseCase(private val dictionaryRepository: DictionaryRepository) {

    fun getGeneralDictionary(): LiveData<List<GeneralWord>> {
        return dictionaryRepository.getGeneralDictionary()
    }
}