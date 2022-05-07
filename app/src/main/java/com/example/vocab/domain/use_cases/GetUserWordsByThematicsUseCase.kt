package com.example.vocab.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.UserWord

class GetUserWordsByThematicsUseCase(private val dictionaryRepository: DictionaryRepository) {

    fun getUserWordsByThematics(thematics: String): LiveData<List<UserWord>> {
        return dictionaryRepository.getUserWordsByThematics(thematics)
    }
}