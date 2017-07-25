package com.github.reyurnible.news.component.scene.favorites

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.reyurnible.news.component.adapter.article.ArticleAdapter
import com.github.reyurnible.news.component.adapter.article_source.ArticleSourceAdapter
import com.github.reyurnible.news.extension.addEndScrollListener
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

/**
 * Favorite Articles Scene Component
 */
class FavoritesFragmentComponent(
        var listener: SourcesFragmentComponentListener? = null
) : AnkoComponent<FavoritesFragment> {
    lateinit var adapter: ArticleAdapter

    override fun createView(ui: AnkoContext<FavoritesFragment>): View = with(ui) {
        adapter = ArticleAdapter(ui.ctx)
        verticalLayout {
            recyclerView {
                adapter = this@FavoritesFragmentComponent.adapter
                layoutManager = LinearLayoutManager(ui.ctx)
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
