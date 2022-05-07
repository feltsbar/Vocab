package com.example.vocab.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vocab.data.dao.GeneralDictionaryDao
import com.example.vocab.data.dao.UserDictionaryDao
import com.example.vocab.data.entities.GeneralWordDbModel
import com.example.vocab.data.entities.UserWordDbModel

@Database(
    entities = [GeneralWordDbModel::class, UserWordDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appGeneralDictionaryDao(): GeneralDictionaryDao
    abstract fun appUserDictionaryDao(): UserDictionaryDao

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }
        }
    }
}