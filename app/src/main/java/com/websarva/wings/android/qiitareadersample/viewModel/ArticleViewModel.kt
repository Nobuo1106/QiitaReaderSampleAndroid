package com.websarva.wings.android.qiitareadersample.viewModel

import com.websarva.wings.android.qiitareadersample.repository.ArticleRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.websarva.wings.android.qiitareadersample.model.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {
    val articleList = MutableLiveData<List<Article>>()
    val errorMessage = MutableLiveData<String>()

    private var currentPage = 1
    private val itemsPerPage = 10

    fun getArticles() {
        val call = repository.getArticles(currentPage, itemsPerPage)
        call.enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                val currentArticles = articleList.value?.toMutableList() ?: mutableListOf()
                response.body()?.let { currentArticles.addAll(it) }
                articleList.postValue(currentArticles)
                currentPage++
            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}