package com.github.reyurnible.news.component.scene.articles

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.reyurnible.news.component.scene.articles.ArticlesActivity
import com.github.reyurnible.news.component.viewholder.ArticleAdapter
import com.github.reyurnible.news.component.viewholder.SourceAdapter
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout
import kotlin.properties.Delegates

/**
 * Articles Component
 */
class ArticlesFragmentComponent(
        var listener: ArticlesFragmentComponent? = null
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
                layoutManager = LinearLayoutManager(ui.ctx, LinearLayoutManager.HORIZONTAL, false)
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                        recyclerView ?: return
                        when (newState) {
                            RecyclerView.SCROLL_STATE_IDLE -> {
                                (layoutManager as? LinearLayoutManager)?.run {
                                    if (findLastVisibleItemPosition() + OFFSET_PAGE_REACH > layoutManager.itemCount) {
                                        listener?.onScrollReached(layoutManager.itemCount)
                                    }
                                }
                            }
                        }
                    }
                })
            }
        }
    }

    interface ArticlesFragmentComponent {
        fun onScrollReached(count: Int)
    }

}
