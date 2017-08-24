package com.github.reyurnible.news.helper

import com.github.reyurnible.news.BuildConfig
import com.github.reyurnible.news.factory.articleFactory
import com.github.reyurnible.news.factory.articleSourceFactory
import com.github.salomonbrys.kodein.Kodein
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(18), manifest = "src/main/AndroidManifest.xml")
abstract class MockTestCase<T>(private val doMock: Boolean) : CanUseEasyMockito<T> {
    open val factory: Kodein = Kodein {
        import(articleFactory)
        import(articleSourceFactory)
    }

    @Before
    open fun setUp() {
        if (doMock) {
            initMocks()
        }
    }

    @After
    open fun clean() {
        validate()
    }
}
