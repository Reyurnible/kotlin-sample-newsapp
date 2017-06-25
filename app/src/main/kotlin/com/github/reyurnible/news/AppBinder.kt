package com.github.reyurnible.news

import com.github.reyurnible.news.repository.NewsApiClient
import com.github.reyurnible.news.repository.NewsRepository
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.generic
import com.github.salomonbrys.kodein.singleton

/**
 * Application DI Binder
 */
object AppBinder {

    inline fun <reified T : Any> bind(tag: Any? = null) = kodein.Instance<T>(generic(), tag)

    val kodein: Kodein = Kodein {
        bind<NewsRepository>() with singleton {
            provideNewsRepository()
        }
    }

    private fun provideNewsRepository(): NewsRepository = NewsRepository(NewsApiClient)

}
