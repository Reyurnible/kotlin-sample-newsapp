package com.github.reyurnible.news.component.viewholder

import android.view.ViewGroup
import org.jetbrains.anko.*

/**
 * ArticleAdapter ViewHolder Component
 */
class ArticleViewHolderComponent : org.jetbrains.anko.AnkoComponent<ViewGroup> {
    var titleText: android.widget.TextView by kotlin.properties.Delegates.notNull()
    var authorText: android.widget.TextView by kotlin.properties.Delegates.notNull()
    var descriptionText: android.widget.TextView by kotlin.properties.Delegates.notNull()
    var thumbnailImage: android.widget.ImageView by kotlin.properties.Delegates.notNull()

    override fun createView(ui: org.jetbrains.anko.AnkoContext<ViewGroup>): android.view.View = with(ui) {
        verticalLayout {
            thumbnailImage = imageView {
                layoutParams = android.widget.LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        dip(160)
                )
                scaleType = android.widget.ImageView.ScaleType.CENTER_CROP
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
