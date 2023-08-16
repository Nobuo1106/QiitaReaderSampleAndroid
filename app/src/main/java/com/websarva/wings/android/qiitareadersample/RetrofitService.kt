package com.websarva.wings.android.qiitareadersample

import com.websarva.wings.android.qiitareadersample.model.ArticleList
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitService {
    @GET("")
    fun getAllArticles(): Call<ArticleList>
    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://qiita.com/api/v2/items")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}