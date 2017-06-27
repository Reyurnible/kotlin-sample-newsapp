package com.github.reyurnible.news.component.scene.home

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.view.View
import com.github.reyurnible.news.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

/**
 * Home Scene Component
 */
class HomeFragmentComponent : AnkoComponent<HomeFragment> {
    lateinit var pagerAdapter: HomePagerAdapter

    override fun createView(ui: AnkoContext<HomeFragment>): View = with(ui) {
        pagerAdapter = HomePagerAdapter(ui.owner.childFragmentManager, emptyList())
        verticalLayout {
            val tabLayout = tabLayout {
                tabMode = TabLayout.MODE_SCROLLABLE
                tabTextColors = ColorStateList.valueOf(Color.BLACK)
            }.lparams(matchParent, dimen(R.dimen.size))
            viewPager {
                id = R.id.home_viewpager
                adapter = pagerAdapter
            }.lparams(matchParent, 0, weight = 1F).apply {
                tabLayout.setupWithViewPager(this, true)
            }
        }
    }


}
