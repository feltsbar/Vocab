package com.example.vocab.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.vocab.domain.DictionaryRepository
import com.example.vocab.domain.entities.Word

class DictionaryRepositoryImpl(application: Application) : DictionaryRepository {

    private val userDictionaryDao = AppDatabase.getInstance(application).appUserDictionaryDao()
    private val generalDictionaryDao =
        AppDatabase.getInstance(application).appGeneralDictionaryDao()
    private val mapper = DictionaryMapper()
//    private val scope = CoroutineScope(Dispatchers.IO)
//
//    init {
//        scope.launch {
//            addUserWord(UserWord(value = "can", translate = "Мочь", thematics = "None"))
//        }
//    }

    override fun getUserDictionary(): LiveData<List<Word>> =
        Transformations.map(
            userDictionaryDao.getUserDictionary()
        ) {
            mapper.mapUserListDbModelToListEntity(it)
        }

    override fun getUserWordsByThematics(thematics: String): LiveData<List<Word>> =
        Transformations.map(
            userDictionaryDao.getUserWordByThematics(thematics)
        ) {
            mapper.mapUserListDbModelToListEntity(it)
        }

    override suspend fun getUserWord(userWordId: Long): Word {
        val dbModel = userDictionaryDao.getUserWord(userWordId)
        return mapper.mapDbModelToUserWordEntity(dbModel)
    }

    override suspend fun addUserWord(word: Word) {
        userDictionaryDao.addUserWord(mapper.mapUserWordEntityToDbModel(word))
    }

    override suspend fun deleteUserWord(word: Word) {
        userDictionaryDao.deleteUserWord(word.id)
    }

    override fun getGeneralDictionary(): LiveData<List<Word>> = Transformations.map(
        generalDictionaryDao.getGeneralDictionary()
    ) {
        mapper.mapGeneralListDbModelToListEntity(it)
    }

    override fun getGeneralWordsByThematics(thematics: String): LiveData<List<Word>> =
        Transformations.map(
            generalDictionaryDao.getGeneralWordByThematics(thematics)
        ) {
            mapper.mapGeneralListDbModelToListEntity(it)
        }

    override suspend fun getGeneralWord(wordId: Long): Word {
        val dbModel = generalDictionaryDao.getGeneralWord(wordId)
        return mapper.mapDbModelToGeneralWordEntity(dbModel)
    }
}