package com.github.reyurnible.news.source

import com.github.reyurnible.news.repository.entity.Article
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Local News Sources
 */
interface LocalNewsSource {

    fun getFavoriteArticles(): Single<List<Article>>

    fun addFavoriteArticle(article: Article): Completable

    fun deleteFavoriteArticle(article: Article): Completable
}
