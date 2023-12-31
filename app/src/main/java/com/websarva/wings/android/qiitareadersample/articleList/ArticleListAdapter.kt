package com.websarva.wings.android.qiitareadersample.articleList

import DateUtils
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.websarva.wings.android.qiitareadersample.WebViewActivity
import com.websarva.wings.android.qiitareadersample.databinding.LayoutRvItemBinding
import com.websarva.wings.android.qiitareadersample.model.Article

class ArticleListAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var articles = mutableListOf<Article>()

    fun setArticleList(articles: List<Article>) {
        // 更新前の記事数がスタートインデックス
        val startPosition = this.articles.size
        // 取得した記事を追加
        this.articles.addAll(articles)
        // 追加分を合わせた記事数
        val itemCount = articles.size
        // 追加前~追加後までのrecyclerViewを更新
        notifyItemRangeInserted(startPosition, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = LayoutRvItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    // ビューにバインディング
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.binding.articleTitle.text = article.title
        holder.binding.articleDate.text = DateUtils.formatDate(article.date)
        holder.binding.articleUserName.text = article.user.id
        Glide.with(holder.binding.root.context)
            .load(article.user.imgUrl)
            .into(holder.binding.articleImage)
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root.context, WebViewActivity::class.java)
            intent.putExtra("url", article.url)
            holder.binding.root.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}

class MainViewHolder(val binding: LayoutRvItemBinding) : RecyclerView.ViewHolder(binding.root)
