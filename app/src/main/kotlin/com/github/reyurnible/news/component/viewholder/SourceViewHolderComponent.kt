package com.github.reyurnible.news.component.viewholder

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
import kotlin.properties.Delegates

/**
 * SourceAdapter ViewHolder Component
 */
class SourceViewHolderComponent : AnkoComponent<ViewGroup> {
    var logoImage: ImageView by Delegates.notNull()
    var nameText: TextView by Delegates.notNull()
    var descriptionText: TextView by Delegates.notNull()

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            logoImage = imageView {
                layoutParams = LinearLayout.LayoutParams(
                        dip(160),
                        dip(160)
                ).apply {
                    this.gravity = Gravity.CENTER_HORIZONTAL
                }
                scaleType = ImageView.ScaleType.FIT_CENTER
            }
            nameText = textView {
                layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
                text = "Name"
            }
            descriptionText = textView {
                layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
                text = "Description"
            }
        }
    }
}
