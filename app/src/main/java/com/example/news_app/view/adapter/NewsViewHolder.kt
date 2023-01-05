package com.example.news_app.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_app.databinding.ItemNewsBinding
import com.example.news_app.domain.entity.News

class NewsViewHolder(private val binding: ItemNewsBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News, onItemClick:((News) -> Unit)?) = binding.run {
        textItemNewsTitle.text = news.title
        textItemNewsDesc.text = news.description
        Glide.with(itemView.context)
            .load(news.imageUrl)
            .centerCrop()
            .into(imageItemNews)
        root.setOnClickListener{
            onItemClick?.invoke(news)
        }
    }
}