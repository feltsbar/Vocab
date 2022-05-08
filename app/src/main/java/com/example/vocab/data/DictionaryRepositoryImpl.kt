package com.example.vocab.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.example.vocab.data.entities.GeneralWordDbModel
import com.example.vocab.data.entities.UserWordDbModel
import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.GeneralWord
import com.example.vocab.domain.entities.UserWord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DictionaryRepositoryImpl(application: Application): DictionaryRepository {

    private val userDictionaryDao = AppDatabase.getInstance(application).appUserDictionaryDao()
    private val generalDictionaryDao = AppDatabase.getInstance(application).appGeneralDictionaryDao()
    private val mapper = DictionaryMapper()
    private val scope = CoroutineScope(Dispatchers.IO)

//    init {
//        scope.launch {
//            addUserWord(UserWord(value = "can", translate = "Мочь", thematics = "None"))
//        }
//    }

    override fun getUserDictionary(): LiveData<List<UserWord>> = Transformations.map(
        userDictionaryDao.getUserDictionary()
    ) {
        mapper.mapUserListDbModelToListEntity(it)
    }

    override fun getUserWordsByThematics(thematics: String): LiveData<List<UserWord>> =
        MediatorLiveData<List<UserWord>>().apply {
            addSource(userDictionaryDao.getUserDictionary()) {
                val sortedList : MutableList<UserWordDbModel> = mutableListOf()
                for (i in it){
                    if(i.thematics == thematics){
                        sortedList.add(i)
                    }
                }
                value = mapper.mapUserListDbModelToListEntity(sortedList as List<UserWordDbModel>)
            }
        }

    override suspend fun getUserWord(userWordId: Long): UserWord {
        val dbModel = userDictionaryDao.getUserWord(userWordId)
        return mapper.mapDbModelToUserWordEntity(dbModel)
    }

    override suspend fun addUserWord(userWord: UserWord) {
        userDictionaryDao.addUserWord(mapper.mapUserWordEntityToDbModel(userWord))
    }

    override suspend fun deleteUserWord(userWord: UserWord) {
        userDictionaryDao.deleteUserWord(userWord.id)
    }

    override fun getGeneralDictionary(): LiveData<List<GeneralWord>> = Transformations.map(
        generalDictionaryDao.getGeneralDictionary()
    ) {
        mapper.mapGeneralListDbModelToListEntity(it)
    }

    override fun getGeneralWordsByThematics(thematics: String): LiveData<List<GeneralWord>> =
        MediatorLiveData<List<GeneralWord>>().apply {
            addSource(generalDictionaryDao.getGeneralDictionary()) {
                val sortedList : MutableList<GeneralWordDbModel> = mutableListOf()
                for (i in it){
                    if(i.thematics == thematics){
                        sortedList.add(i)
                    }
                }
                value = mapper.mapGeneralListDbModelToListEntity(sortedList as List<GeneralWordDbModel>)
            }
        }

    override suspend fun getGeneralWord(wordId: Long): GeneralWord {
        val dbModel = generalDictionaryDao.getGeneralWord(wordId)
        return mapper.mapDbModelToGeneralWordEntity(dbModel)
    }
}