package com.example.vocab.domain.entities

data class UserWord (
    val id: Long = UNDEFINED_ID,
    val value: String,
    val translate: String,
    val thematics: String
) {
    companion object{
        const val UNDEFINED_ID: Long = 0
    }

    //
}