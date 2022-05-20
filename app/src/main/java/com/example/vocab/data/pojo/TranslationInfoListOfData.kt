package com.example.vocab.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TranslationInfoListOfData (
    @SerializedName("translations")
    @Expose
    val translationsList: List<TranslationInfo>? = null
)