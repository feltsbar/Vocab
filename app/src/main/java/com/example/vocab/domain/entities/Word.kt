package com.example.vocab.domain.entities

data class Word (
    val id: Long = UNDEFINED_ID,
    val value: String,
    val translate: String,
    val thematics: String
) {
    companion object{
        const val UNDEFINED_ID: Long = 0
    }
}

