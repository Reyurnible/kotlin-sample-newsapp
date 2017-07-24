package com.github.reyurnible.news.component.scene.articles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.reyurnible.news.extension.setContentFragment
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.jetbrains.anko.setContentView

/**
 * Articles Scene
 */
class ArticlesActivity : RxAppCompatActivity() {
    private object Key {
        const val sourceId = "sourceId"
    }

    companion object {
        fun createIntent(context: Context, sourceId: String): Intent = Intent(context, ArticlesActivity::class.java).apply {
            putExtra(Key.sourceId, sourceId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set Content View
        val component: ArticlesActivityComponent = ArticlesActivityComponent()
        component.setContentView(this)
        val sourceId = intent.getStringExtra(Key.sourceId)
        savedInstanceState ?: setContentFragment(fragment = ArticlesFragment.createInstance(sourceId))
    }
}
