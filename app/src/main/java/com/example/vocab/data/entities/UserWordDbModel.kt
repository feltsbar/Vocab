package com.example.vocab.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_dictionary")
data class UserWordDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "value", collate = ColumnInfo.NOCASE)
    val value: String,

    @ColumnInfo(name = "translate")
    val translate: String,

    @ColumnInfo(name = "thematics")
    val thematics: String
)