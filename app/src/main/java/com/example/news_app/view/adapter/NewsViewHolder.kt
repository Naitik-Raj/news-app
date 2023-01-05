package com.example.news_app.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.news_app.databinding.ItemNewsBinding
import com.example.news_app.domain.entity.News
import com.example.news_app.util.loadUrl

class NewsViewHolder(private val binding: ItemNewsBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News, onItemClick:((News) -> Unit)?) = binding.run {
        textItemNewsTitle.text = news.title
        textItemNewsDesc.text = news.description

        imageItemNews.loadUrl(news.imageUrl)

        root.setOnClickListener{
            onItemClick?.invoke(news)
        }
    }
}