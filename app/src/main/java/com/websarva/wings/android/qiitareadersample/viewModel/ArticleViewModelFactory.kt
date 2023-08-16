package com.websarva.wings.android.qiitareadersample.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.websarva.wings.android.qiitareadersample.repository.ArticleRepository


class ArticleViewModelFactory constructor(private val repository: ArticleRepository) : ViewModelProvider.Factory {
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            ArticleViewModel(this.repository) as T
        } else throw IllegalArgumentException("ViewModel Not Found")
    }
}