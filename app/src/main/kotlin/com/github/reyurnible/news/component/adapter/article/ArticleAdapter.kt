package com.github.reyurnible.news.component.adapter.article

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.reyurnible.news.component.item.ArticleComponent
import com.github.reyurnible.news.repository.entity.Article
import org.jetbrains.anko.AnkoContext
import java.util.*

/**
 * Article Adapter
 */
class ArticleAdapter(
        private val context: Context,
        var articles: ArrayList<Article> = ArrayList(),
        var onItemClickListener: ((Article) -> Unit)? = null
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? =
            ViewHolder(ArticleComponent(), AnkoContext.Companion.create(context, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.article = articles[position]
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
