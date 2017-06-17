package com.github.reyurnible.news.component.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.reyurnible.news.entity.Article
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import java.util.*

/**
 * Article Adapter
 */
class ArticleAdapter(
        private val context: Context,
        val articles: ArrayList<Article> = ArrayList()
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ArticleAdapter.ViewHolder? =
            ViewHolder(ArticleViewHolderComponent(), AnkoContext.Companion.create(context, parent, false))

    override fun onBindViewHolder(holder: com.github.reyurnible.news.component.viewholder.ArticleAdapter.ViewHolder, position: Int) {
        holder.article = articles[position]
    }

    inner class ViewHolder(
            private val component: ArticleViewHolderComponent,
            ankoContext: AnkoContext<ViewGroup>
    ) : RecyclerView.ViewHolder(component.createView(ankoContext)) {
        var article: Article? = null
            set(value) {
                field = value
                bindArticle(value)
            }

        private fun bindArticle(value: Article?) {
            value ?: return
            Picasso.with(context).load(value.urlToImage).into(component.thumbnailImage)
            component.titleText.text = value.title
            component.descriptionText.text = value.description
            component.authorText.text = value.author
        }

    }

}
