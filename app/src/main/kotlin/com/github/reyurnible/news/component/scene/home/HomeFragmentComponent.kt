package com.github.reyurnible.news.component.scene.home

import android.view.View
import com.github.reyurnible.news.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

/**
 * Home Scene Component
 */
class HomeFragmentComponent : AnkoComponent<HomeFragment>, HomeView {
    lateinit var pagerAdapter: HomePagerAdapter

    override fun createView(ui: AnkoContext<HomeFragment>): View = with(ui) {
        pagerAdapter = HomePagerAdapter(ui.owner.childFragmentManager, emptyList())
        verticalLayout {
            val tabLayout = tabLayout {

            }.lparams(matchParent, dimen(R.dimen.size))
            viewPager {
                adapter = pagerAdapter
            }.lparams(matchParent, 0, weight = 1F).apply {
                tabLayout.setupWithViewPager(this, true)
            }
        }
    }


}
