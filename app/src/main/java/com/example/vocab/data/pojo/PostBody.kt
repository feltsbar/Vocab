package com.example.vocab.data.pojo

data class PostBody(
    val folderId: String = "", //todo Вписать FOLDER_ID
    val texts: List<String> = listOf("Hello"),
    val targetLanguageCode: String = "ru",
    val sourceLanguageCode: String = "en"
)