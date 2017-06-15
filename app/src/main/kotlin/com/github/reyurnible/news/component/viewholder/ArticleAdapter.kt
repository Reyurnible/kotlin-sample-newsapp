package com.github.reyurnible.news.component.viewholder

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.reyurnible.news.component.viewholder.ArticleViewHolderComponent
import com.github.reyurnible.news.entity.Article
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import java.util.*

/**
 * Article Adapter
 */
class ArticleAdapter(
        private val activity: android.app.Activity,
        val articles: java.util.ArrayList<Article> = java.util.ArrayList()
) : android.support.v7.widget.RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): com.github.reyurnible.news.component.viewholder.ArticleAdapter.ViewHolder? =
            ViewHolder(com.github.reyurnible.news.component.viewholder.ArticleViewHolderComponent(), org.jetbrains.anko.AnkoContext.Companion.create(activity, parent, false))

    override fun onBindViewHolder(holder: com.github.reyurnible.news.component.viewholder.ArticleAdapter.ViewHolder, position: Int) {
        holder.article = articles[position]
    }

    inner class ViewHolder(
            private val component: com.github.reyurnible.news.component.viewholder.ArticleViewHolderComponent,
            ankoContext: org.jetbrains.anko.AnkoContext<ViewGroup>
    ) : android.support.v7.widget.RecyclerView.ViewHolder(component.createView(ankoContext)) {
        var article: com.github.reyurnible.news.entity.Article? = null
            set(value) {
                field = value
                bindArticle(value)
            }

        private fun bindArticle(value: com.github.reyurnible.news.entity.Article?) {
            value ?: return
            com.squareup.picasso.Picasso.with(activity).load(value.urlToImage).into(component.thumbnailImage)
            component.titleText.text = value.title
            component.descriptionText.text = value.description
            component.authorText.text = value.author
        }

    }

}
