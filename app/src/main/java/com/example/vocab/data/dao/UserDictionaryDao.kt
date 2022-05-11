package com.example.vocab.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vocab.data.entities.UserWordDbModel

@Dao
interface UserDictionaryDao {
    @Query("SELECT * FROM user_dictionary")
    fun getUserDictionary(): LiveData<List<UserWordDbModel>>

    @Query("SELECT * FROM user_dictionary WHERE thematics IS :thematics")
    suspend fun getUserWordByThematics(thematics: String): List<UserWordDbModel>

    @Query("SELECT * FROM user_dictionary WHERE id IS :wordId")
    suspend fun getUserWord(wordId: Long): UserWordDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserWord(userWordDbModel: UserWordDbModel)

    @Query("DELETE FROM user_dictionary WHERE id=:wordId")
    suspend fun deleteUserWord(wordId: Long)

}