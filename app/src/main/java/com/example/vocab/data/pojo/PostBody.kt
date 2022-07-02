package com.example.vocab.data.pojo

data class PostBody(
    val folderId: String = "b1gdti20dbhqdln0pmdo", //todo Вписать FOLDER_ID
    val texts: List<String> = listOf("Hello"),
    val targetLanguageCode: String = "ru",
    val sourceLanguageCode: String = "en"
)