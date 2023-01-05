package com.example.news_app.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.news_app.domain.repository.NewsRepository
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class NewsViewModel(private val newsRepository: NewsRepository):ViewModel() {
    val topHeadline = newsRepository.topHeadline.asLiveData(context = viewModelScope.coroutineContext)

    fun getTopHeadline() = viewModelScope.launch {
        newsRepository.getTopHeadlines()
    }
    companion object{
        fun modules():Module{
            return module {
                viewModel{ NewsViewModel(get()) }
            }
        }
    }

}