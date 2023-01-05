package com.example.news_app.domain.repository

import com.example.news_app.data.repository.NewsRepositoryImpl
import com.example.news_app.domain.entity.News
import com.example.news_app.event.StateEvent
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.Module
import org.koin.dsl.module

interface NewsRepository {
    val topHeadline: Flow<StateEvent<List<News>>>

    suspend fun getTopHeadlines()
    suspend fun invalidate()

    fun sendErrorFromExceptionHandler(throwable: Throwable)
    companion object{
        fun modules(): Module {
            return module(){
                single<NewsRepository>{ NewsRepositoryImpl(get()) }
            }
        }
    }
}