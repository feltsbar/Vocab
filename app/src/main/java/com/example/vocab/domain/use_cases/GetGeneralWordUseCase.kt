package com.example.vocab.domain.use_cases

import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class GetGeneralWordUseCase(private val dictionaryRepository: DictionaryRepository){

    suspend fun getGeneralWord(wordId: Long): Word {
        return dictionaryRepository.getGeneralWord(wordId)
    }
}