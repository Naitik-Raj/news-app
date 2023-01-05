package com.example.news_app.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_app.databinding.ActivityMainBinding
import com.example.news_app.event.StateEvent
import com.example.news_app.util.onFailure
import com.example.news_app.util.onSuccess
import com.example.news_app.view.adapter.NewsAdapter
import com.example.news_app.view.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: NewsViewModel by viewModel()
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        newsAdapter.onItemClick = { news ->
            Toast.makeText(this, news.title, Toast.LENGTH_SHORT).show()
        }

        viewModel.getTopHeadline()

        viewModel.topHeadline.observe(this) { newsEvent ->
            binding.progressCircular.isVisible = newsEvent is  StateEvent.Loading
            binding.layoutError.root.isVisible = newsEvent is  StateEvent.Failure

            newsEvent
                .onSuccess {
                newsAdapter.news = it
            }
                .onFailure {
                    binding.layoutError.textError.text = it.message
                    binding.layoutError.btnErrorTryAgain.setOnClickListener{
                        viewModel.getTopHeadline()
                    }
                }
        }
    }
    private fun setupView() = binding.apply {
            recyclerViewNews.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewNews.adapter = newsAdapter
        }
}