package com.example.vocab.domain.use_cases.user_dictionary

import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class GetUserWordsByThematicsUseCase(private val dictionaryRepository: DictionaryRepository) {

    suspend fun getUserWordsByThematics(thematics: String): List<Word> {
        return dictionaryRepository.getUserWordsByThematics(thematics)
    }
}