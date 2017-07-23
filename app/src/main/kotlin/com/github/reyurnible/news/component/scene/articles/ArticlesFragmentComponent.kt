package com.github.reyurnible.news.component.scene.articles

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.reyurnible.news.component.adapter.article.ArticleAdapter
import com.github.reyurnible.news.extension.addEndScrollListener
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

/**
 * Articles Component
 */
class ArticlesFragmentComponent(
        var listener: ArticlesFragmentComponentListener? = null
) : AnkoComponent<ArticlesFragment> {
    companion object {
        const val OFFSET_PAGE_REACH = 2
    }

    // Adapter
    lateinit var adapter: ArticleAdapter

    override fun createView(ui: AnkoContext<ArticlesFragment>): View = with(ui) {
        adapter = ArticleAdapter(ui.ctx)
        verticalLayout {
            recyclerView {
                adapter = this@ArticlesFragmentComponent.adapter
                layoutManager = LinearLayoutManager(ui.ctx)
                addEndScrollListener {
                    listener?.onScrollReached(layoutManager.itemCount)
                }
            }
        }
    }

    interface ArticlesFragmentComponentListener {
        fun onScrollReached(count: Int)
    }

}
