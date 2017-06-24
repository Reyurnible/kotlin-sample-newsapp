package com.github.reyurnible.news

import com.github.reyurnible.news.repository.NewsRepository

/**
 * Application DI Component
 */
interface AppComponents {

    fun provideNewsRepository(): NewsRepository

}
