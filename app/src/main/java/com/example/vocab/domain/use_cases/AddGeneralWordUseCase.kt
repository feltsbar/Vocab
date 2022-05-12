package com.example.vocab.domain.use_cases

import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class AddGeneralWordUseCase(private val dictionaryRepository: DictionaryRepository) {

    suspend fun addGeneralWord(word : Word) {
        dictionaryRepository.addGeneralWord(word)
    }

}