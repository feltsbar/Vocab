package com.example.vocab.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vocab.data.entities.GeneralWordDbModel

@Dao
interface GeneralDictionaryDao {
    @Query("SELECT * FROM general_dictionary")
    fun getGeneralDictionary(): LiveData<List<GeneralWordDbModel>>

    @Query("SELECT * FROM general_dictionary WHERE thematics IS :thematics")
    suspend fun getGeneralWordByThematics(thematics: String): List<GeneralWordDbModel>

    @Query("SELECT * FROM general_dictionary WHERE id IS :wordId")
    suspend fun getGeneralWord(wordId: Long): GeneralWordDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGeneralWord(generalWordDbModel: GeneralWordDbModel)

    @Query("DELETE FROM general_dictionary WHERE id=:wordId")
    suspend fun deleteGeneralWord(wordId: Long)

    @Query("DELETE FROM general_dictionary")
    suspend fun deleteAllFromGeneralDictionary()
}