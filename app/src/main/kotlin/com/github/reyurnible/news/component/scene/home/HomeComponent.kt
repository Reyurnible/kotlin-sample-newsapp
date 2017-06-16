package com.github.reyurnible.news.component.scene.home

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Home Scene Component
 */
class HomeComponent : AnkoComponent<HomeFragment>, HomeView {

    override fun createView(ui: AnkoContext<HomeFragment>): View = with(ui) {
        recyclerView {
            layoutManager = LinearLayoutManager(ui.ctx)
        }
    }


}