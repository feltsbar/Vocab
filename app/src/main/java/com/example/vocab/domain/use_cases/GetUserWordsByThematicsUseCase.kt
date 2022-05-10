package com.example.vocab.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class GetUserWordsByThematicsUseCase(private val dictionaryRepository: DictionaryRepository) {

    fun getUserWordsByThematics(thematics: String): LiveData<List<Word>> {
        return dictionaryRepository.getUserWordsByThematics(thematics)
    }
}