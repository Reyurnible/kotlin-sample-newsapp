package com.github.reyurnible.news.repository

import com.github.reyurnible.news.repository.entity.Article
import com.github.reyurnible.news.repository.entity.ArticleSource
import io.reactivex.Completable
import io.reactivex.Single

/**
 * News Repository
 */
interface NewsRepository {

    fun getArticles(source: String, sortBy: String? = null): Single<List<Article>>

    fun getFavoriteArticles(): Single<List<Article>>

    fun addFavoriteArticle(article: Article): Completable

    fun getSources(category: String? = null, language: String? = null, country: String? = null): Single<List<ArticleSource>>
}
