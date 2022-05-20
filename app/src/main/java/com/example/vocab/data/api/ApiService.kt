package com.example.vocab.data.api

import com.example.vocab.data.pojo.TranslationInfoListOfData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("translate")
    fun getTranslatedText(
        @Query(QUERY_BODY_FOLDER_ID) folderId : String = "b1gdti20dbhqdln0pmdo",
        @Query(QUERY_BODY_TEXTS) texts : List<String> = listOf(""),
        @Query(QUERY_BODY_TARGET_LANGUAGE)targetLanguageCode: String,
        @Query(QUERY_BODY_SOURCE_LANGUAGE)sourceLanguageCode: String
    ): Single<TranslationInfoListOfData>

    companion object {
        private const val QUERY_HEADERS_API_KEY = "Api_Key"

        private const val QUERY_BODY_FOLDER_ID = "folderId"
        private const val QUERY_BODY_TEXTS = "texts"
        private const val QUERY_BODY_SOURCE_LANGUAGE = "sourceLanguageCode"
        private const val QUERY_BODY_TARGET_LANGUAGE = "targetLanguageCode"

    }

//    private const val ACCOUNT_FOLDER_ID = "b1gdti20dbhqdln0pmdo"
//    @Query(QUERY_PARAM_API_KEY) api_key : String = "AQVNynCv645-tF0Xs4QlpIGQlc03Hp47D5KVfpKx",
}