package com.github.reyurnible.news.component.adapter.article_source

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.reyurnible.news.component.adapter.RecyclerDiffCallback
import com.github.reyurnible.news.component.item.ArticleSourceComponent
import com.github.reyurnible.news.repository.entity.ArticleSource
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.AnkoContext
import kotlin.properties.Delegates

/**
 * Source Adapter
 */
class ArticleSourceAdapter(
        private val context: Context,
        var onItemClickListener: ((ArticleSource) -> Unit)? = null
) : RecyclerView.Adapter<ArticleSourceAdapter.ViewHolder>() {
    var sources: List<ArticleSource> by Delegates.observable(emptyList(), { prop, oldItem, newItem ->
        updateAsync(oldItem, newItem)
    })

    override fun getItemCount(): Int = sources.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? =
            ViewHolder(ArticleSourceComponent(), AnkoContext.create(context, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.source = sources[position]
    }

    private fun updateAsync(oldItem: List<ArticleSource>, newItem: List<ArticleSource>) {
        launch(UI) {
            val deferred = async { calculateDiff(oldItem, newItem) }
            val result = deferred.await()
            result.dispatchUpdatesTo(this@ArticleSourceAdapter)
        }
    }

    private suspend fun calculateDiff(oldItem: List<ArticleSource>, newItem: List<ArticleSource>): DiffUtil.DiffResult  {
        val callback = RecyclerDiffCallback(oldItem, newItem, isSameItem = { oldItem, newItem ->
            oldItem?.id == newItem?.id
        })
        return DiffUtil.calculateDiff(callback)
    }

    inner class ViewHolder(
            private val component: ArticleSourceComponent,
            ankoContext: AnkoContext<ViewGroup>
    ) : RecyclerView.ViewHolder(component.createView(ankoContext)) {
        var source: ArticleSource? = null
            set(value) {
                field = value
                component.source = value
            }

        init {
            itemView.setOnClickListener {
                source?.let { onItemClickListener?.invoke(it) }
            }
        }

    }

}
