package com.github.reyurnible.news.component.item

import android.support.v4.widget.TextViewCompat
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.reyurnible.news.R
import com.github.reyurnible.news.repository.entity.ArticleSource
import org.jetbrains.anko.*

/**
 * ArticleSource Item Component
 */
class ArticleSourceComponent : AnkoComponent<ViewGroup> {
    var source: ArticleSource? = null
        set(value) {
            field = value
            value?.let(this::bind)
        }

    private lateinit var nameText: TextView
    private lateinit var descriptionText: TextView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            nameText = textView {
                TextViewCompat.setTextAppearance(this, R.style.TextAppearance_AppCompat_Title)
            }.lparams(matchParent, wrapContent)
            descriptionText = textView {
                maxLines = 3
                ellipsize = TextUtils.TruncateAt.END
                TextViewCompat.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body1)
            }.lparams(matchParent, wrapContent)
        }
    }

    private fun bind(articleSource: ArticleSource) {
        nameText.text = articleSource.name
        descriptionText.text = articleSource.description
    }


}
