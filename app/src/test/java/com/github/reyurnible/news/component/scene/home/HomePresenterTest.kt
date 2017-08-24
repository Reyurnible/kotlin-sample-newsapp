package com.github.reyurnible.news.component.scene.home

import com.github.reyurnible.news.helper.MockTestCase
import com.github.reyurnible.news.helper.given
import com.github.reyurnible.news.repository.DomainError
import com.github.reyurnible.news.repository.NewsRepository
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.salomonbrys.kodein.instance
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mock

/**
 * Top Presenter Test
 */
sealed class TopPresenterTest : MockTestCase<HomePresenter>(doMock = true) {
    @Mock
    lateinit var view: HomeView
    @Mock
    lateinit var sceneDataHolder: HomePresenter.DataHolder
    @Mock
    lateinit var newsRepository: NewsRepository

    override fun createTestTarget(): HomePresenter = HomePresenterImpl(view, sceneDataHolder, newsRepository)

    @Test
    fun testOnCreateSuccess() = given {
        val responseFactory: (Int) -> List<ArticleSource> = factory.instance()
        val response = responseFactory(10)

        Want(Single.just(response)).When(newsRepository).getSources()

        it.onCreate()

        val testObserver = sceneDataHolder.sourceList.asObservable().test()
        testObserver.awaitTerminalEvent()
        testObserver
                .assertNoErrors()
                .assertValue { it == response }
    }

    @Test
    fun testOnCreateFailure() = given {
        val errorResponse: HttpException = factory.instance("Server")

        Want(Single.error<List<ArticleSource>>(errorResponse)).When(newsRepository).getSources()

        it.onCreate()

        val domainError: DomainError.Api = factory.instance<(Int) -> DomainError.Api>()(500)
        view.verifyInvocations(1).showError(domainError)
    }

}
