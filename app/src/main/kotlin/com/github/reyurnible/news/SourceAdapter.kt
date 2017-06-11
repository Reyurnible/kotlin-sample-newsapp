package com.github.reyurnible.news

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.reyurnible.news.component.ArticleViewHolderComponent
import com.github.reyurnible.news.component.SourceViewHolderComponent
import com.github.reyurnible.news.entity.Article
import com.github.reyurnible.news.entity.Source
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import java.util.*

/**
 * Source Adapter
 */
class SourceAdapter(
        private val activity: Activity,
        val sources: ArrayList<Source> = ArrayList()
) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    override fun getItemCount(): Int = sources.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? =
            ViewHolder(SourceViewHolderComponent(), AnkoContext.create(activity, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.source = sources[position]
    }

    inner class ViewHolder(
            private val component: SourceViewHolderComponent,
            ankoContext: AnkoContext<ViewGroup>
    ) : RecyclerView.ViewHolder(component.createView(ankoContext)) {
        var source: Source? = null
            set(value) {
                field = value
                bindSource(value)
            }

        private fun bindSource(value: Source?) {
            value ?: return
            Picasso.with(activity).load(value.logoUrls?.small).into(component.logoImage)
            component.nameText.text = value.name
            component.descriptionText.text = value.description
        }

    }

}
