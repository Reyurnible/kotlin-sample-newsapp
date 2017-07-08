package com.github.reyurnible.news.source.remote

import com.github.reyurnible.news.entity.Article
import com.github.reyurnible.news.entity.ArticleSource
import io.reactivex.Single

/**
 * News Sources
 */
interface NewsRemoteSource {

    fun getArticles(
            source: String,
            sortBy: String?
    ): Single<List<Article>>

    fun getSources(
            category: String?,
            language: String?,
            country: String?
    ): Single<List<ArticleSource>>

}
