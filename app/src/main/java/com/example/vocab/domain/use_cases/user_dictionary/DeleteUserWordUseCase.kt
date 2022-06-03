package com.example.vocab.domain.use_cases.user_dictionary

import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class DeleteUserWordUseCase(private val dictionaryRepository: DictionaryRepository) {

    suspend fun deleteUserWord(wordId: Long){
        dictionaryRepository.deleteUserWord(wordId)
    }
}