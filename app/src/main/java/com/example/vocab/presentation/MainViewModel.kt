package com.example.vocab.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.example.vocab.data.DictionaryRepositoryImpl
import com.example.vocab.domain.entities.Word
import com.example.vocab.domain.use_cases.GetGeneralDictionaryUseCase
import com.example.vocab.domain.use_cases.GetGeneralWordsByThematicsUseCase
import com.example.vocab.domain.use_cases.GetUserDictionaryUseCase
import java.lang.RuntimeException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DictionaryRepositoryImpl(application)
    private val getGeneralWordsByThematicsUseCase = GetGeneralWordsByThematicsUseCase(repository)
    private val getGeneralDictionaryUseCase = GetGeneralDictionaryUseCase(repository)
    private val getUserDictionaryUseCase = GetUserDictionaryUseCase(repository)

    // TODO: Реализовать отображение списка исходных тем на главном экране приложения!
    // LiveData реализована по паттерну Синглтон, а значит на нее можно подключить наблюдателя!
    // значит нужно использовать список слов, завернутых в LiveData !!!
    // Сложность заключается в том, что на вход приходит список Word а на выходе я жду список строк

    // Из медиатора вернуть список слов каждое из которых с уникальной тематикой, а далее в
    // методе распарсить этот список ???

    // Убрать LiveData из возвращаемого значения методов GetUserDictionaryByThematics

    val userDictionary = getUserDictionaryUseCase.getUserDictionary()

    fun getGeneralThematics(): List<String> {
        val startList = getGeneralDictionaryUseCase.getGeneralDictionary().value
        val resultMap = mutableMapOf<String, MutableList<Word>>()
        if (startList == null) {
            throw RuntimeException("General dictionary is empty!")
        } else {
            for (word in startList) {
                if (resultMap.containsKey(word.thematics)) {
                    resultMap[word.thematics]?.add(word)
                } else {
                    resultMap[word.thematics] = mutableListOf(word)
                }
            }
        }
        return resultMap.keys.toList()
    }

}