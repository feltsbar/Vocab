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

    override fun getUserDictionary(): LiveData<List<Word>> =
        Transformations.map(
            userDictionaryDao.getUserDictionary()
        ) {
            mapper.mapUserListDbModelToListEntity(it)
        }

    override suspend fun getUserWordsByThematics(thematics: String): List<Word> =
        mapper.mapUserListDbModelToListEntity(userDictionaryDao.getUserWordByThematics(thematics))

    override suspend fun getUserWord(wordId: Long): Word {
        val dbModel = userDictionaryDao.getUserWord(wordId)
        return mapper.mapDbModelToUserWordEntity(dbModel)
    }

    override suspend fun deleteUserWord(wordId: Long) {
        userDictionaryDao.deleteUserWord(wordId)
    }

    override suspend fun addUserWord(word: Word) {
        userDictionaryDao.addUserWord(mapper.mapUserWordEntityToDbModel(word))
    }

    override suspend fun addGeneralWord(word: Word) {
        generalDictionaryDao.addGeneralWord(mapper.mapGeneralWordEntityToDbModel(word))
    }

    override fun getGeneralDictionary(): LiveData<List<Word>> = Transformations.map(
        generalDictionaryDao.getGeneralDictionary()
    ) {
        mapper.mapGeneralListDbModelToListEntity(it)
    }

    override suspend fun getGeneralWordsByThematics(thematics: String): List<Word> =
        mapper.mapGeneralListDbModelToListEntity(
            generalDictionaryDao.getGeneralWordByThematics(
                thematics
            )
        )

    override suspend fun getGeneralWord(wordId: Long): Word {
        val dbModel = generalDictionaryDao.getGeneralWord(wordId)
        return mapper.mapDbModelToGeneralWordEntity(dbModel)
    }

    override suspend fun deleteGeneralWord(wordId: Long) {
        generalDictionaryDao.deleteGeneralWord(wordId)
    }

    override suspend fun deleteAllFromGeneralDictionary() {
        generalDictionaryDao.deleteAllFromGeneralDictionary()
    }
}