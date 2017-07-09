package com.github.reyurnible.news

import com.github.reyurnible.news.repository.NewsRepository
import com.github.reyurnible.news.repository.NewsRepositoryImpl
import com.github.reyurnible.news.source.LocalNewsSource
import com.github.reyurnible.news.source.RemoteNewsSource
import com.github.reyurnible.news.source.local_requery.LocalNewsSourceImpl
import com.github.reyurnible.news.source.remote.NewsApiClient
import com.github.reyurnible.news.source.remote.RemoteNewsSourceImpl
import com.github.salomonbrys.kodein.*

/**
 * Application DI Binder
 */
object AppBinder {

    inline fun <reified T : Any> bind(tag: Any? = null) = kodein.Instance<T>(generic(), tag)

    val kodein: Kodein = Kodein {
        bind<RemoteNewsSource>() with singleton { provideRemoteNewsSource() }

        bind<LocalNewsSource>() with singleton { provideLocalNewsSource() }

        bind<NewsRepository>() with singleton { provideNewsRepository(instance(), instance()) }
    }

    private fun provideRemoteNewsSource(): RemoteNewsSource = RemoteNewsSourceImpl(NewsApiClient.newsApi)

    private fun provideLocalNewsSource(): LocalNewsSource = LocalNewsSourceImpl()

    private fun provideNewsRepository(
            localNewsSource: LocalNewsSource,
            remoteNewsSource: RemoteNewsSource
    ): NewsRepository = NewsRepositoryImpl(localNewsSource, remoteNewsSource)

}
