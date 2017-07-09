package com.github.reyurnible.news.source.remote

import com.github.reyurnible.news.repository.entity.Article
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.reyurnible.news.source.RemoteNewsSource
import com.github.reyurnible.news.source.remote.response.GetArticlesResponse
import com.github.reyurnible.news.source.remote.response.GetSourcesResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * News Sources Impl
 */
class NewsRemoteSourceImpl(private val newsApi: NewsApi) : RemoteNewsSource {

    override fun getArticles(
            source: String,
            sortBy: String?
    ): Single<List<Article>> =
            newsApi
                    .getArticles(source, NewsApiClient.API_KEY, sortBy)
                    .map(GetArticlesResponse::articles)
                    .subscribeOn(Schedulers.io())

    override fun getSources(
            category: String?,
            language: String?,
            country: String?
    ): Single<List<ArticleSource>> =
            newsApi
                    .getSources(category, language, country)
                    .map(GetSourcesResponse::sources)
                    .subscribeOn(Schedulers.io())

}
