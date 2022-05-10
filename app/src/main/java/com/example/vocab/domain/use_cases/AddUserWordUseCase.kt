package com.example.vocab.domain.use_cases

import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class AddUserWordUseCase(private val dictionaryRepository: DictionaryRepository) {

    suspend fun addUserWord(word: Word){
        dictionaryRepository.addUserWord(word)
    }
}