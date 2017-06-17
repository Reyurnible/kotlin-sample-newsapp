package com.github.reyurnible.news.component.scene.home

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.viewPager

/**
 * Home Scene Component
 */
class HomeFragmentComponent : AnkoComponent<HomeFragment>, HomeView {

    override fun createView(ui: AnkoContext<HomeFragment>): View = with(ui) {
        viewPager {

        }
    }


}