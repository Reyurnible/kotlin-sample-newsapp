package com.github.reyurnible.news.component.scene.articles

import android.os.Bundle
import com.github.reyurnible.news.component.viewholder.SourceAdapter
import com.github.reyurnible.news.repository.NewsApiClient
import com.github.reyurnible.news.repository.NewsRepository
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.jetbrains.anko.setContentView
import kotlin.properties.Delegates

/**
 * Articles Scene
 */
class ArticlesActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set Content View
        val component: ArticlesActivityComponent = ArticlesActivityComponent()
        component.setContentView(this)
    }
}
