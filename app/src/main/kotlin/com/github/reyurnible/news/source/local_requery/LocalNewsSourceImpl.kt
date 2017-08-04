package com.github.reyurnible.news.source.local_requery

import com.github.reyurnible.news.repository.entity.Article
import com.github.reyurnible.news.source.LocalNewsSource
import com.github.reyurnible.news.source.local_requery.dao.ArticleEntity
import io.reactivex.Completable
import io.reactivex.Single
import com.github.reyurnible.news.source.local_requery.dao.Article as LocalArticle

class LocalNewsSourceImpl : LocalNewsSource {

    override fun getFavoriteArticles(): Single<List<Article>> =
            RequeryClient.dataStore.select(LocalArticle::class.java)
                    .get()
                    .observable()
                    .map { it.toEntity() }
                    .toList()

    override fun addFavoriteArticle(article: Article): Completable =
            RequeryClient.dataStore.insert(article.toDao()).toCompletable()

    override fun deleteFavoriteArticle(article: Article): Completable =
            RequeryClient.dataStore.delete(article.toDao())

    private fun Article.toDao(): LocalArticle =
            ArticleEntity().also { dao ->
                dao.title = title
                dao.author = author
                dao.description = description
                dao.url = url
                dao.urlToImage = urlToImage
                dao.publishedAt = publishedAt
            }

    private fun LocalArticle.toEntity(): Article =
            Article(
                    title = title,
                    description = description,
                    author = author,
                    url = url,
                    urlToImage = urlToImage,
                    publishedAt = publishedAt
            )
}
