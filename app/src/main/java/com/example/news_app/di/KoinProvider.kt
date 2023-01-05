package com.example.news_app.di

import android.content.Context
import com.example.news_app.data.network.WebService
import com.example.news_app.domain.repository.NewsRepository
import com.example.news_app.view.viewmodel.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinProvider {

    fun initKoin(context: Context){
        startKoin{
            androidContext(context)
            modules(
                WebService.modules(),//web services module
                NewsRepository.modules(),//repository module
                NewsViewModel.modules()//viewModel module
            )
        }
    }
}