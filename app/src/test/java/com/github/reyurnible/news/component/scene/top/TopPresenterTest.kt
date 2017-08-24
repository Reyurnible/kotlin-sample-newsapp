package com.github.reyurnible.news.component.scene.top

import com.github.reyurnible.news.helper.MockTestCase
import com.github.reyurnible.news.helper.given
import org.junit.Test
import org.mockito.Mock

/**
 * Top Presenter Test
 */
class TopPresenterTest : MockTestCase<TopPresenter>(doMock = true) {
    @Mock
    lateinit var view: TopView

    override fun createTestTarget(): TopPresenter = TopPresenterImpl(view)

    @Test
    fun testOnClickMenuHome() = given {
        it.onClickMenuHome()
        view.verifyInvocations(1).showHome()
    }

    @Test
    fun testOnClickMenuSources() = given {
        it.onClickMenuSources()
        view.verifyInvocations(1).showSources()
    }

    @Test
    fun testOnClickMenuFavorites() = given {
        it.onClickMenuFavorites()
        view.verifyInvocations(1).showFavorites()
    }

}
