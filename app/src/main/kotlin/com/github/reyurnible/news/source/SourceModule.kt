package com.github.reyurnible.news.source

import com.github.reyurnible.news.source.local_requery.LocalNewsSourceImpl
import com.github.reyurnible.news.source.remote.NewsApiClient
import com.github.reyurnible.news.source.remote.RemoteNewsSourceImpl
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.*

/**
 * Source Module
 */
val sourceModule = Kodein.Module {
    bind<LocalNewsSource>() with singleton {
        LocalNewsSourceImpl()
    }

    bind<RemoteNewsSource>() with singleton {
        RemoteNewsSourceImpl(NewsApiClient.newsApi)
    }
}