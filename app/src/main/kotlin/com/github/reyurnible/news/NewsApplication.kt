package com.github.reyurnible.news

import android.app.Application
import com.github.reyurnible.news.repository.NewsApiClient
import com.github.reyurnible.news.repository.NewsRepository
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

/**
 * NewsApplication
 */
class NewsApplication : Application(), AppComponents {

    val kodein: Kodein = Kodein {
        bind<NewsRepository>() with singleton {
            provideNewsRepository()
        }
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun provideNewsRepository(): NewsRepository =
            NewsRepository(apiClient = NewsApiClient)

}
