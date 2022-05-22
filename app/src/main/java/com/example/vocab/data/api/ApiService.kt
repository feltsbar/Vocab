package com.example.vocab.data.api

import com.example.vocab.data.pojo.PostBody
import com.example.vocab.data.pojo.TranslationInfoListOfData
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("translate")
    @Headers(
        "Content-Type: application/json",
        "Authorization: Api-Key " //todo Вписать API_KEY
    )
    fun getTranslatedText(@Body postBody: PostBody): Single<TranslationInfoListOfData>


}