package com.websarva.wings.android.qiitareadersample.utils

import com.websarva.wings.android.qiitareadersample.model.Article
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

interface RetrofitService {
    @GET("items")
    fun getArticles(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<Article>>

    companion object {
        private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        //RetroFit Debug用
        private val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(logger)
            addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer 818187039180032e9d0ca44c2a3056ced1d12fac")
                    .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }

        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                httpClient.addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer 818187039180032e9d0ca44c2a3056ced1d12fac")
                        .method(original.method, original.body)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://qiita.com/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}