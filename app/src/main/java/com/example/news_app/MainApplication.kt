package com.example.news_app

import android.app.Application
import com.example.news_app.di.KoinProvider

class MainApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        KoinProvider.initKoin(this)
    }
}