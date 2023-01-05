package com.example.news_app.data.repository

import com.example.news_app.data.Mapper
import com.example.news_app.data.network.WebService
import com.example.news_app.domain.entity.News
import com.example.news_app.domain.repository.NewsRepository
import com.example.news_app.event.StateEvent
import com.example.news_app.util.asFlowEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect

class NewsRepositoryImpl(private val webService: WebService): NewsRepository {
    private val _topHeadline: MutableStateFlow<StateEvent<List<News>>> =
        MutableStateFlow(StateEvent.Idle())

    override val topHeadline: Flow<StateEvent<List<News>>>
        get() = _topHeadline

    override suspend fun getTopHeadlines() {
        val loadingEvent = StateEvent.Loading<List<News>>()
        _topHeadline.value = loadingEvent

        webService.getTopHeadline(
            category = "technology"
        ).asFlowEvent {
            Mapper.mapResponseToNews(it)
        }.collect(_topHeadline)
    }

    override suspend fun invalidate() {
        _topHeadline.value = StateEvent.Idle()
    }

}