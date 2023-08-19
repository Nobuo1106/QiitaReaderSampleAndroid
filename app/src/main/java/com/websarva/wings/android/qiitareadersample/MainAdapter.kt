package com.websarva.wings.android.qiitareadersample

import DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.websarva.wings.android.qiitareadersample.databinding.LayoutRvItemBinding
import com.websarva.wings.android.qiitareadersample.model.Article

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var articles = mutableListOf<Article>()

    fun setArticleList(articles: List<Article>) {
        this.articles = articles.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = LayoutRvItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.binding.articleTitle.text = article.title
        holder.binding.articleDate.text = DateUtils.formatDate(article.date)
        holder.binding.articleUserName.text = article.user.id
        Glide.with(holder.binding.root.context)
            .load(article.user.imgUrl)
            .into(holder.binding.articleImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}

class MainViewHolder(val binding: LayoutRvItemBinding) : RecyclerView.ViewHolder(binding.root)
