package com.example.vocab.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TranslationInfo(
    @SerializedName("text")
    @Expose
    val text : String? = null
)