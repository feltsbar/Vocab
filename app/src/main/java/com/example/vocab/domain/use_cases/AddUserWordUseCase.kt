package com.example.vocab.domain.use_cases

import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.UserWord

class AddUserWordUseCase(private val dictionaryRepository: DictionaryRepository) {

    suspend fun addUserWord(userWord: UserWord){
        dictionaryRepository.addUserWord(userWord)
    }
}