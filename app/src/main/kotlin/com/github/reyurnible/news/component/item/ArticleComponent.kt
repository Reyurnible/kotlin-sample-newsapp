package com.github.reyurnible.news.component.item

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.reyurnible.news.extension.load
import com.github.reyurnible.news.repository.entity.Article
import org.jetbrains.anko.*

/**
 * Article Component
 */
class ArticleComponent : AnkoComponent<ViewGroup> {
    var article: Article? = null
        set(value) {
            field = value
            value?.let(this::bind)
        }

    private lateinit var titleText: TextView
    private lateinit var authorText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var thumbnailImage: ImageView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            thumbnailImage = imageView {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }.lparams(matchParent, dip(160))
            titleText = textView {

            }
            authorText = textView {

            }
            descriptionText = textView {

            }
        }
    }

    fun bind(article: Article) {
        thumbnailImage.load(article.urlToImage)
        titleText.text = article.title
        descriptionText.text = article.description
        authorText.text = article.author
    }

}
