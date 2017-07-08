package com.github.reyurnible.news.repository

import com.github.reyurnible.news.entity.Article
import com.github.reyurnible.news.entity.ArticleSource
import com.github.reyurnible.news.repository.response.GetArticlesResponse
import com.github.reyurnible.news.repository.response.GetSourcesResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * News Repository
 */
class NewsRepository(private val apiClient: NewsApiClient) {

    fun getArticles(
            source: String,
            sortBy: String? = null
    ): Single<List<Article>> =
            apiClient.newsApi
                    .getArticles(source, apiClient.API_KEY, sortBy)
                    .map(GetArticlesResponse::articles)
                    .subscribeOn(Schedulers.io())

    fun getSources(
            category: String? = null,
            language: String? = null,
            country: String? = null
    ): Single<List<ArticleSource>> =
            apiClient.newsApi
                    .getSources(category, language, country)
                    .map(GetSourcesResponse::sources)
                    .subscribeOn(Schedulers.io())

}
