package com.github.reyurnible.news.component.adapter.article

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.reyurnible.news.component.adapter.RecyclerDiffCallback
import com.github.reyurnible.news.component.item.ArticleComponent
import com.github.reyurnible.news.repository.entity.Article
import com.github.reyurnible.news.repository.entity.ArticleSource
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.AnkoContext
import java.util.*
import kotlin.properties.Delegates

/**
 * Article Adapter
 */
class ArticleAdapter(
        private val context: Context,
        var onItemClickListener: ((Article) -> Unit)? = null
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    var articles: List<Article> by Delegates.observable(emptyList(), { prop, oldItem, newItem ->
        updateAsync(oldItem, newItem)
    })

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? =
            ViewHolder(ArticleComponent(), AnkoContext.Companion.create(context, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.article = articles[position]
    }

    private fun updateAsync(oldItem: List<Article>, newItem: List<Article>) {
        launch(UI) {
            val deferred = async { calculateDiff(oldItem, newItem) }
            val result = deferred.await()
            result.dispatchUpdatesTo(this@ArticleAdapter)
        }
    }

    private suspend fun calculateDiff(oldItem: List<Article>, newItem: List<Article>): DiffUtil.DiffResult  {
        val callback = RecyclerDiffCallback(oldItem, newItem, isSameItem = { oldItem, newItem ->
            oldItem?.title == newItem?.title
        })
        return DiffUtil.calculateDiff(callback)
    }

    inner class ViewHolder(
            private val component: ArticleComponent,
            ankoContext: AnkoContext<ViewGroup>
    ) : RecyclerView.ViewHolder(component.createView(ankoContext)) {
        var article: Article? = null
            set(value) {
                field = value
                component.article = value
            }

        init {
            itemView.setOnClickListener {
                article?.let {
                    this@ArticleAdapter.onItemClickListener?.invoke(it)
                }
            }
        }
    }
}
