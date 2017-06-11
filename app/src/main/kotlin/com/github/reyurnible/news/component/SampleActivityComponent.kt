package com.github.reyurnible.news.component

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.github.reyurnible.news.SampleActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout
import kotlin.properties.Delegates

/**
 * Sample Activity Component
 */
class SampleActivityComponent : AnkoComponent<SampleActivity> {
    var titleBar: Toolbar by Delegates.notNull()
    var recyclerView: RecyclerView by Delegates.notNull()

    override fun createView(ui: AnkoContext<SampleActivity>): View = with(ui) {
        verticalLayout {
            titleBar = toolbar {

            }
            recyclerView = recyclerView {
                layoutManager = LinearLayoutManager(ui.owner, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

}
