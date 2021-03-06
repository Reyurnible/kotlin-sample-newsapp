package com.github.reyurnible.news.component.scene.article_sources

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.github.reyurnible.news.component.adapter.article_source.ArticleSourceAdapter
import com.github.reyurnible.news.extension.addEndScrollListener
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

/**
 * Sources Scene Component
 */
class ArticleSourcesFragmentComponent(
        var listener: SourcesFragmentComponentListener? = null
) : AnkoComponent<ArticleSourcesFragment> {
    companion object {
        const val GRID_COLUMNS = 3
        const val OFFSET_PAGE_REACH = 2
    }

    lateinit var adapter: ArticleSourceAdapter

    override fun createView(ui: AnkoContext<ArticleSourcesFragment>): View = with(ui) {
        adapter = ArticleSourceAdapter(ui.ctx)
        verticalLayout {
            recyclerView {
                adapter = this@ArticleSourcesFragmentComponent.adapter
                layoutManager = GridLayoutManager(ui.ctx, GRID_COLUMNS)
                addEndScrollListener {
                    listener?.onScrollReached(it.layoutManager.itemCount)
                }
            }
        }
    }

    interface SourcesFragmentComponentListener {
        fun onScrollReached(count: Int)
    }

}
