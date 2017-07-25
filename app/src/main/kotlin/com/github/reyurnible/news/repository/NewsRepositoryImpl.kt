package com.github.reyurnible.news.repository

import com.github.reyurnible.news.repository.entity.Article
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.reyurnible.news.source.LocalNewsSource
import com.github.reyurnible.news.source.RemoteNewsSource
import io.reactivex.Observable
import io.reactivex.Single

/**
 * News Repository Impl
 */
class NewsRepositoryImpl(
        private val localNewsSource: LocalNewsSource,
        private val remoteNewsSource: RemoteNewsSource
) : NewsRepository {
    override fun getArticles(source: String, sortBy: String?): Single<List<Article>> =
            remoteNewsSource.getArticles(source, sortBy)

    override fun getFavoriteArticles(): Single<List<Article>> =
            localNewsSource.getFavoriteArticles()

    override fun getSources(category: String?, language: String?, country: String?): Single<List<ArticleSource>> =
            remoteNewsSource.getSources(category, language, country)
}
