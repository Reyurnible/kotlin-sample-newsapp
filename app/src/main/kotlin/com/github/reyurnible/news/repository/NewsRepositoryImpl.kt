package com.github.reyurnible.news.repository

import com.github.reyurnible.news.repository.entity.Article
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.reyurnible.news.source.LocalNewsSource
import com.github.reyurnible.news.source.RemoteNewsSource
import io.reactivex.Completable
import io.reactivex.Single

/**
 * News Repository Impl
 */
class NewsRepositoryImpl(
        private val localNewsSource: LocalNewsSource,
        private val remoteNewsSource: RemoteNewsSource
) : NewsRepository {
    private var sourcesCache: List<ArticleSource>? = null

    override fun getArticles(source: String, sortBy: String?): Single<List<Article>> =
            remoteNewsSource.getArticles(source, sortBy)
                    .addDomainErrorHandle()

    override fun getFavoriteArticles(): Single<List<Article>> =
            localNewsSource.getFavoriteArticles()
                    .addDomainErrorHandle()

    override fun addFavoriteArticle(article: Article): Completable =
            localNewsSource.addFavoriteArticle(article)
                    .addDomainErrorHandle()

    override fun getSources(category: String?, language: String?, country: String?): Single<List<ArticleSource>> =
            sourcesCache?.let {
                Single.just(it)
            } ?: let {
                remoteNewsSource.getSources(category, language, country)
                        .addDomainErrorHandle()
                        .doOnSuccess { sourcesCache = it }
            }
}
