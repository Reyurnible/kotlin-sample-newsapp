package com.github.reyurnible.news.component.scene.articles

import android.os.Bundle
import com.github.reyurnible.news.SourceAdapter
import com.github.reyurnible.news.repository.NewsApiClient
import com.github.reyurnible.news.repository.NewsRepository
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.setContentView
import kotlin.properties.Delegates

class ArticlesActivity : RxAppCompatActivity() {
    // Adapter
    private var adapter: SourceAdapter by Delegates.notNull()
    // Repository
    private var newsRepository: NewsRepository = NewsRepository(NewsApiClient)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set Content View
        val component: ArticlesActivityComponent = ArticlesActivityComponent()
        component.setContentView(this)
        adapter = SourceAdapter(this)
        component.recyclerView.apply {
            adapter = this@ArticlesActivity.adapter
        }
        newsRepository.getSources()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    adapter.sources.addAll(it)
                    adapter.notifyDataSetChanged()
                }
    }
}
