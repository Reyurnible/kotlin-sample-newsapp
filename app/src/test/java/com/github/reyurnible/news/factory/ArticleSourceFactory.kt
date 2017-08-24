package com.github.reyurnible.news.factory

import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.salomonbrys.kodein.*
import java.util.*

/**
 * Create Article
 */
val articleSourceFactory = Kodein.Module {
    bind<List<ArticleSource>>() with factory { size: Int ->
        (0 until size).map {
            instance<ArticleSource>()
        }
    }

    bind<ArticleSource>() with provider {
        ArticleSource(
                id = "abc-news-au",
                name = "ABC News (AU)",
                description = "Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.",
                url = "http://www.abc.net.au/news",
                category = "general",
                language = "en",
                country = "au",
                sortBysAvailable = arrayListOf("top")
        )
    }
}
