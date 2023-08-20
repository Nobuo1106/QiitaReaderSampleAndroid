package com.websarva.wings.android.qiitareadersample.repository

import com.websarva.wings.android.qiitareadersample.model.Article
import com.websarva.wings.android.qiitareadersample.utils.RetrofitService
import retrofit2.Call

class ArticleRepository(private val retrofitService: RetrofitService) {
    fun getArticles(page: Int, perPage: Int): Call<List<Article>> {
        return retrofitService.getArticles(page, perPage)
    }
}