package com.github.reyurnible.news.component.adapter.article_source

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.reyurnible.news.component.item.ArticleSourceComponent
import com.github.reyurnible.news.repository.entity.ArticleSource
import org.jetbrains.anko.AnkoContext

/**
 * Source Adapter
 */
class ArticleSourceAdapter(
        private val context: Context,
        var sources: List<ArticleSource> = emptyList(),
        var onItemClickListener: ((ArticleSource) -> Unit)? = null
) : RecyclerView.Adapter<ArticleSourceAdapter.ViewHolder>() {

    override fun getItemCount(): Int = sources.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? =
            ViewHolder(ArticleSourceComponent(), AnkoContext.create(context, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.source = sources[position]
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
