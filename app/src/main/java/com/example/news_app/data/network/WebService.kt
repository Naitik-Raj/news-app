package com.example.news_app.data.network

import com.example.news_app.data.entity.NewsResponse
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET(EndPoint.TOP_HEADLINE)
    suspend fun getTopHeadline(
        @Query(QueryParam.COUNTRY) country:String = "in",
        @Query(QueryParam.API_KEY) apiKey:String = "226adfe008df47d4b85acfae1713a421",
        @Query(QueryParam.CATEGORY) category:String

    ):Response<NewsResponse>

    object EndPoint{
        const val TOP_HEADLINE = "/v2/top-headlines"
    }
    object QueryParam{
        const val COUNTRY = "country"
        const val CATEGORY = "category"
        const val API_KEY = "apiKey"
    }
    companion object{
        private const val BASE_URL = "https://newsapi.org/"

        private fun create(): WebService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }
        fun modules(): Module{
            return module{
                single { create() }
            }
        }
    }

}