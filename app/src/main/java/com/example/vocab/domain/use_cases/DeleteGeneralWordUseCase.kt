package com.example.vocab.domain.use_cases

import com.example.vocab.domain.DictionaryRepository

class DeleteGeneralWordUseCase (private val dictionaryRepository: DictionaryRepository) {
    suspend fun deleteGeneralWord(wordId: Long){
        dictionaryRepository.deleteGeneralWord(wordId)
    }

}
