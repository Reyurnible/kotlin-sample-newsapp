package com.github.reyurnible.news.source

import com.github.reyurnible.news.repository.entity.Article
import com.github.reyurnible.news.repository.entity.ArticleSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Local News Sources
 */
interface LocalNewsSource {

    fun getArticles(source: String, sortBy: String?): Observable<Article>

    fun getSources(category: String?, language: String?, country: String?): Observable<List<ArticleSource>>

    fun setArticles(source: String, articles: List<Article>): Completable

    fun setSources(category: String?, language: String?, country: String?, articleSources: List<ArticleSource>): Completable

    fun getFavoriteArticles(): Single<List<Article>>

    fun addFavoriteArticle(article: Article)

    fun deleteFavoriteArticle(article: Article)
}
