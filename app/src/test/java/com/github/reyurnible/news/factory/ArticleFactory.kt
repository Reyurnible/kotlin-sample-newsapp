package com.github.reyurnible.news.factory

import com.github.reyurnible.news.repository.entity.Article
import com.github.salomonbrys.kodein.*
import java.util.*

/**
 * Create Article
 */
val articleFactory = Kodein.Module {
    constant("now") with Date()

    bind<List<Article>>() with factory { size: Int ->
        (0 until size).map {
            instance<Article>()
        }
    }

    bind<Article>() with provider {
        Article(
                title = "Samsung tried to spin the Note 7 disaster into gold at its Note 8 event",
                description = "From time to time, Samsung strikes advertising pay dirt. Earlier this year, the company had a pretty good spot when it stuck a Gear VR on an ostrich’s head...",
                author = "Brian Heater",
                url = "https://techcrunch.com/2017/08/24/samsung-tried-to-spin-the-note-7-disaster-into-gold-at-its-note-8-event/",
                urlToImage = "https://tctechcrunch2011.files.wordpress.com/2017/08/screen-shot-2017-08-23-at-5-15-59-pm.png",
                publishedAt = instance("now")
        )
    }
}
