package com.example.vocab.domain.use_cases

import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.UserWord

class DeleteUserWordUseCase(private val dictionaryRepository: DictionaryRepository) {

    suspend fun deleteUserWord(userWord: UserWord){
        dictionaryRepository.deleteUserWord(userWord)
    }
}