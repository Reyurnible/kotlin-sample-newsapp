package com.github.reyurnible.news.extension

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.reyurnible.news.component.scene.sources.SourcesFragmentComponent

/**
 * RecyclerView Extensions
 */
fun RecyclerView.addEndScrollListener(listener: (RecyclerView) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            val layoutManager = (recyclerView?.layoutManager as? LinearLayoutManager) ?: return
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> {
                    if (layoutManager.findLastVisibleItemPosition() + SourcesFragmentComponent.OFFSET_PAGE_REACH > layoutManager.itemCount) {
                        listener.invoke(this@addEndScrollListener)
                    }
                }
            }
        }
    })

}
