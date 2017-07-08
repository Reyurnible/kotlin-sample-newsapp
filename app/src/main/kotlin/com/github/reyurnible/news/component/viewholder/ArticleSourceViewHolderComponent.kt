package com.github.reyurnible.news.component.viewholder

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.*
import kotlin.properties.Delegates

/**
 * SourceAdapter ViewHolder Component
 */
class ArticleSourceViewHolderComponent : AnkoComponent<ViewGroup> {
    var logoImage: ImageView by Delegates.notNull()
    var nameText: TextView by Delegates.notNull()
    var descriptionText: TextView by Delegates.notNull()

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            logoImage = imageView {
                scaleType = ImageView.ScaleType.FIT_CENTER
                adjustViewBounds = true
            }.lparams(matchParent, wrapContent)
            nameText = textView {
                text = "Name"
            }.lparams(matchParent, wrapContent)
            descriptionText = textView {
                text = "Description"
                maxLines = 3
                ellipsize = TextUtils.TruncateAt.END
            }.lparams(matchParent, wrapContent)
        }
    }
}
