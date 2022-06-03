package com.example.vocab.domain.use_cases.general_dictionary

import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class GetGeneralWordsByThematicsUseCase(private val dictionaryRepository: DictionaryRepository) {

    suspend fun getGeneralWordsByThematics(thematics: String): List<Word> {
        return dictionaryRepository.getGeneralWordsByThematics(thematics)
    }
}