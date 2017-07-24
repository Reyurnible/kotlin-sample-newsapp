package com.github.reyurnible.news.component.scene.articles

import android.view.View
import android.widget.FrameLayout
import com.github.reyurnible.news.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.verticalLayout

/**
 * Articles Component
 */
class ArticlesActivityComponent : AnkoComponent<ArticlesActivity> {
    lateinit var containerLayout: FrameLayout

    override fun createView(ui: AnkoContext<ArticlesActivity>): View = with(ui) {
        verticalLayout {
            toolbar {

            }
            containerLayout = frameLayout {
                id = R.id.layout_container
            }
        }
    }

}
