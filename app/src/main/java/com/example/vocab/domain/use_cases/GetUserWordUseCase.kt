package com.example.vocab.domain.use_cases

import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class GetUserWordUseCase(private val dictionaryRepository: DictionaryRepository) {

    suspend fun getUserWord(userWordId: Long): Word {
        return dictionaryRepository.getUserWord(userWordId)
    }
}