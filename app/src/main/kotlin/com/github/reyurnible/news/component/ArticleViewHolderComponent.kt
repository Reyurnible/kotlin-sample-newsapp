package com.github.reyurnible.news.component

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
import kotlin.properties.Delegates

/**
 * ArticleAdapter ViewHolder Component
 */
class ArticleViewHolderComponent : AnkoComponent<ViewGroup> {
    var titleText: TextView by Delegates.notNull()
    var authorText: TextView by Delegates.notNull()
    var descriptionText: TextView by Delegates.notNull()
    var thumbnailImage: ImageView by Delegates.notNull()

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            thumbnailImage = imageView {
                layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dip(160)
                )
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
            titleText = textView {
                text = "Title"
            }
            authorText = textView {
                text = "Author"
            }
            descriptionText = textView {
                text = "Description"
            }
        }
    }
}
