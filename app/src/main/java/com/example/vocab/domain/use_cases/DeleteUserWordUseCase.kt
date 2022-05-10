package com.example.vocab.domain.use_cases

import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class DeleteUserWordUseCase(private val dictionaryRepository: DictionaryRepository) {

    suspend fun deleteUserWord(word: Word){
        dictionaryRepository.deleteUserWord(word)
    }
}