package com.example.vocab.domain.use_cases.general_dictionary

import com.example.vocab.domain.DictionaryRepository

class DeleteAllFromGeneralDictionary(private val dictionaryRepository: DictionaryRepository) {
    suspend fun deleteAllFromGeneralDictionary(){
        dictionaryRepository.deleteAllFromGeneralDictionary()
    }
}