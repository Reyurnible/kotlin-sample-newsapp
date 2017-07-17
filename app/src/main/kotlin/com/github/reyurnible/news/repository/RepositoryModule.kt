package com.github.reyurnible.news.repository

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.*

/**
 * Repository Module
 */
val repositoryModule = Kodein.Module {
    bind<NewsRepository>() with singleton {
        NewsRepositoryImpl(instance(), instance())
    }
}