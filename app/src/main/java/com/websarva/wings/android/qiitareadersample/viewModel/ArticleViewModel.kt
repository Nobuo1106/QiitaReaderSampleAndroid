package com.websarva.wings.android.qiitareadersample.viewModel

import com.websarva.wings.android.qiitareadersample.repository.ArticleRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.websarva.wings.android.qiitareadersample.model.Article
import com.websarva.wings.android.qiitareadersample.model.ArticleList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {
    val articleList = MutableLiveData<List<Article>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllArticles()
        response.enqueue(object : Callback<ArticleList> {
            override fun onResponse(call: Call<ArticleList>, response: Response<ArticleList>) {
                articleList.postValue(response.body()?.articleList)
            }

            override fun onFailure(call: Call<ArticleList>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}