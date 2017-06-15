package com.github.reyurnible.news.component.scene.articles

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.reyurnible.news.component.scene.articles.ArticlesActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout
import kotlin.properties.Delegates

/**
 * Articles Component
 */
class ArticlesActivityComponent : AnkoComponent<ArticlesActivity> {
    var titleBar: Toolbar by Delegates.notNull()
    var recyclerView: RecyclerView by Delegates.notNull()

    override fun createView(ui: AnkoContext<ArticlesActivity>): View = with(ui) {
        verticalLayout {
            titleBar = toolbar {

            }
            recyclerView = recyclerView {
                layoutManager = LinearLayoutManager(ui.owner, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

}
