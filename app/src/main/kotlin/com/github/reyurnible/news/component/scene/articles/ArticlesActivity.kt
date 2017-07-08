package com.github.reyurnible.news.component.scene.articles

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.jetbrains.anko.setContentView

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
