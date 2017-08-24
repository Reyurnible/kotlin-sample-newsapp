package com.github.reyurnible.news.repository

import com.github.reyurnible.news.helper.MockTestCase
import com.github.reyurnible.news.helper.given
import com.github.reyurnible.news.repository.entity.Article
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.reyurnible.news.source.LocalNewsSource
import com.github.reyurnible.news.source.RemoteNewsSource
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.instance
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mock

/**
 * News Repository Test
 */
class NewsRepositoryTest : MockTestCase<NewsRepository>(true) {
    @Mock
    lateinit var localSource: LocalNewsSource
    @Mock
    lateinit var remoteSource: RemoteNewsSource

    override fun createTestTarget(): NewsRepository = NewsRepositoryImpl(localSource, remoteSource)

    @Test
    fun testGetArticles() = given {
        val source: ArticleSource = factory.instance()
        val responseFactory: (Int) -> List<Article> = factory.factory()
        val response = responseFactory(10)

        Want(Single.just(response)).When(remoteSource).getArticles(source = source.id, sortBy = null)

        val testObserver = it.getArticles(source = source.id).test()
        testObserver.awaitTerminalEvent()
        testObserver
                .assertNoErrors()
                .assertValue { it == response }
    }

    @Test
    fun testGetFavoriteArticles() = given {

    }

    @Test
    fun testAddFavoriteArticle() = given {

    }

    @Test
    fun testGetSources() = given {
        val responseFactory: (Int) -> List<ArticleSource> = factory.factory()
        val response = responseFactory(10)

        Want(Single.just(response)).When(remoteSource).getSources(null, null, null)

        val testObserver = it.getSources().test()
        testObserver.awaitTerminalEvent()
        testObserver
                .assertNoErrors()
                .assertValue { it == response }
    }

}
