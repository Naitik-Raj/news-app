package com.example.news_app.view.activity

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_app.databinding.ActivityMainBinding
import com.example.news_app.event.StateEvent
import com.example.news_app.util.BindingActivity
import com.example.news_app.util.onFailure
import com.example.news_app.util.onSuccess
import com.example.news_app.view.adapter.NewsAdapter
import com.example.news_app.view.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<ActivityMainBinding>() {

    private val viewModel: NewsViewModel by viewModel()
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter()
    }

    private fun setupView() = binding.apply {
            recyclerViewNews.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewNews.adapter = newsAdapter
        }

    override fun inflateBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        setupView()
        newsAdapter.onItemClick = { news ->
            DetailActivity.launch(this,news)
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
}
