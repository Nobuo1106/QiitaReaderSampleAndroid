package com.websarva.wings.android.qiitareadersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.websarva.wings.android.qiitareadersample.databinding.ActivityMainBinding
import com.websarva.wings.android.qiitareadersample.repository.ArticleRepository
import com.websarva.wings.android.qiitareadersample.viewModel.ArticleViewModel
import com.websarva.wings.android.qiitareadersample.viewModel.ArticleViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: ArticleViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            //set recyclerview adapter
            recyclerview.adapter = adapter

            //set layout manager for recyclerview
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        }
        setContentView(binding.root)

        //get viewmodel instance using MyViewModelFactory
        viewModel =
            ViewModelProvider(this, ArticleViewModelFactory(ArticleRepository(retrofitService))).get(
                ArticleViewModel::class.java
            )

        viewModel.articleList.observe(this, Observer {
            Log.d(TAG, "movieList: $it")
            adapter.setArticleList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "errorMessage: $it")
        })

        viewModel.getAllArticles()
    }
}
