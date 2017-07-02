package com.github.reyurnible.news.component.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.reyurnible.news.entity.Source
import com.github.reyurnible.news.extension.load
import org.jetbrains.anko.AnkoContext

/**
 * Source Adapter
 */
class SourceAdapter(
        private val context: Context,
        val sources: MutableList<Source> = mutableListOf()
) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    override fun getItemCount(): Int = sources.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? =
            ViewHolder(SourceViewHolderComponent(), AnkoContext.create(context, parent, false))

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
            component.logoImage.load(value.logoUrls?.small)
            component.nameText.text = value.name
            component.descriptionText.text = value.description
        }

    }

}
