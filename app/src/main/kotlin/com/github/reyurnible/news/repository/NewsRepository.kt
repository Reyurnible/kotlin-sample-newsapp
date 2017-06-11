package com.github.reyurnible.news.repository

import com.github.reyurnible.news.entity.Article
import com.github.reyurnible.news.entity.Source
import io.reactivex.Observable

/**
 * News Repository
 */
class NewsRepository(private val apiClient: NewsApiClient) {

    fun getArticles(
            source: String,
            sortBy: String? = null
    ): Observable<List<Article>> =
            apiClient.newsApi
                    .getArticles(source, apiClient.API_KEY, sortBy)
                    .map { it.articles }

    fun getSources(
            category: String? = null,
            language: String? = null,
            country: String? = null
    ): Observable<List<Source>> =
            apiClient.newsApi
                    .getSources(category, language, country)
                    .map { it.sources }

}
