package com.github.reyurnible.news.source.local_requery

import com.github.reyurnible.news.repository.entity.Article
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.reyurnible.news.source.LocalNewsSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import com.github.reyurnible.news.source.local_requery.dao.Article as LocalArticle

class LocalNewsSourceImpl : LocalNewsSource {
    override fun getArticles(source: String, sortBy: String?): Observable<Article> =
            RequeryClient.dataStore.select(LocalArticle::class.java)
                    .get()
                    .observable()
                    .map {
                        Article(
                                title = it.title,
                                description = it.description ?: "",
                                author = it.author ?: "",
                                url = it.url ?: "",
                                urlToImage = it.urlToImage ?: "",
                                publishedAt = it.publishedAt ?: Date()
                        )
                    }

    override fun getSources(category: String?, language: String?, country: String?): Observable<List<ArticleSource>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setArticles(source: String, articles: List<Article>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setSources(category: String?, language: String?, country: String?, articleSources: List<ArticleSource>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavoriteArticles(): Single<List<Article>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addFavoriteArticle(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteFavoriteArticle(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
