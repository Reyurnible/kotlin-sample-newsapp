package com.github.reyurnible.news.component.scene.sources

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.github.reyurnible.news.component.viewholder.SourceAdapter
import com.github.reyurnible.news.extension.addEndScrollListener
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

/**
 * Home Scene Component
 */
class SourcesFragmentComponent(
        var listener: SourcesFragmentComponentListener? = null
) : AnkoComponent<SourcesFragment> {
    companion object {
        const val GRID_COLUMNS = 3
        const val OFFSET_PAGE_REACH = 2
    }

    lateinit var adapter: SourceAdapter

    override fun createView(ui: AnkoContext<SourcesFragment>): View = with(ui) {
        adapter = SourceAdapter(ui.ctx)
        verticalLayout {
            recyclerView {
                adapter = this@SourcesFragmentComponent.adapter
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
