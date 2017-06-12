package com.github.reyurnible.news.component.scene.top

import android.os.Bundle
import com.github.reyurnible.news.SourceAdapter
import com.github.reyurnible.news.component.scene.articles.ArticlesActivityComponent
import com.github.reyurnible.news.repository.NewsApiClient
import com.github.reyurnible.news.repository.NewsRepository
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.setContentView
import kotlin.properties.Delegates

class TopActivity : RxAppCompatActivity() {
    lateinit var component: TopActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set Content View
        component = TopActivityComponent()
        component.setContentView(this)
        component.menuItemClickListener = { menuItem ->
            when (menuItem.itemId) {
                else -> false
            }
        }
    }
}
