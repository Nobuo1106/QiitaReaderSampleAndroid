package com.websarva.wings.android.qiitareadersample.repository

import com.websarva.wings.android.qiitareadersample.RetrofitService

class ArticleRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllArticles() = retrofitService.getAllArticles()
}