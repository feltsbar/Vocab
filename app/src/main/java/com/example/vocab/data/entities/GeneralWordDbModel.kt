package com.example.vocab.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "general_dictionary")
data class GeneralWordDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "value", collate = ColumnInfo.NOCASE)
    val value: String,

    @ColumnInfo(name = "translate")
    val translate: String,

    @ColumnInfo(name = "thematics")
    val thematics: String
)