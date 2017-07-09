package com.github.reyurnible.news.source

import com.github.reyurnible.news.repository.entity.Article
import com.github.reyurnible.news.repository.entity.ArticleSource
import io.reactivex.Single

/**
 * Remote News Sources
 */
interface RemoteNewsSource {

    fun getArticles(source: String, sortBy: String?): Single<List<Article>>

    fun getSources(category: String?, language: String?, country: String?): Single<List<ArticleSource>>

}
