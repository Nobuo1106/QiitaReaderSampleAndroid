package com.websarva.wings.android.qiitareadersample.articleList

import InfiniteScrollListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.websarva.wings.android.qiitareadersample.databinding.FragmentArticleListBinding //layout/fragment_article_listから参照している。
import com.websarva.wings.android.qiitareadersample.repository.ArticleRepository
import com.websarva.wings.android.qiitareadersample.utils.RetrofitService
import com.websarva.wings.android.qiitareadersample.viewModel.ArticleViewModel
import com.websarva.wings.android.qiitareadersample.viewModel.ArticleViewModelFactory

class ArticleListFragment : Fragment() {
    // bindingはlayout/fragment_article_listから参照。
    private lateinit var binding: FragmentArticleListBinding

    private lateinit var viewModel: ArticleViewModel

    private val retrofitService = RetrofitService.getInstance()
    private val adapter = ArticleListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleListBinding.inflate(inflater, container, false).apply {
            // アダプターの設定
            recyclerview.adapter = adapter

            // 縦並びに設定
            recyclerview.layoutManager = LinearLayoutManager(context)
            val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            // アイテム毎の区切り線を設定
            recyclerview.addItemDecoration(dividerItemDecoration)

            // イベントリスナーを設定
            val infiniteScrollListener = object : InfiniteScrollListener(recyclerview.layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.getArticles()
                }
            }
            recyclerview.addOnScrollListener(infiniteScrollListener)
        }

        // ビューモデルにリポジトリを注入
        viewModel = ViewModelProvider(this, ArticleViewModelFactory(ArticleRepository(retrofitService))).get(
            ArticleViewModel::class.java
        )

        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            adapter.setArticleList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                Toast.makeText(context, "通信に失敗しました。通信環境が良いところで再度アクセスして下さい。", Toast.LENGTH_LONG).show()
            }
        })

        // 記事をAPIから取得
        viewModel.getArticles()

        return binding.root
    }
}