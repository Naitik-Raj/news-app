package com.example.news_app.view.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import com.example.news_app.databinding.ActivityDetailBinding
import com.example.news_app.domain.entity.News
import com.example.news_app.util.BindingActivity
import com.example.news_app.util.loadUrl

class DetailActivity: BindingActivity<ActivityDetailBinding>(){

    private val news: News by lazy {
        val intentJson = intent.getStringExtra(INTENT_DATA).orEmpty()
        News.fromJsonString(intentJson)
    }
    override fun inflateBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?): Unit = binding.run {
        imgNews.loadUrl(news.imageUrl)
        textTitle.text = news.title
        textDescription.text = news.description
        textContent.text = news.content

        btnReadMore.setOnClickListener{
            openUrl()
        }
    }

    private fun openUrl() {
        val uri = Uri.parse(news.url)
        val builder = CustomTabsIntent.Builder().build()
        builder.launchUrl(this,uri)
    }

    companion object{
        private const val INTENT_DATA = "news_extra"
        fun launch(context: Context,news: News){
            val intent = Intent(context,DetailActivity::class.java)
            val newsJson = news.toJsonString()
            intent.putExtra(INTENT_DATA,newsJson)
            context.startActivity(intent)
        }
    }
}