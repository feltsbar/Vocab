package com.example.vocab.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.GeneralWord

class GetGeneralWordsByThematicsUseCase(private val dictionaryRepository: DictionaryRepository) {

    fun getGeneralWordsByThematics(thematics: String): LiveData<List<GeneralWord>> {
        return dictionaryRepository.getGeneralWordsByThematics(thematics)
    }
}