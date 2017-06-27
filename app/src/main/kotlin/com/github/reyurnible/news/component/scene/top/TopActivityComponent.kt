package com.github.reyurnible.news.component.scene.top

import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.View
import com.github.reyurnible.news.R
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.bottomNavigationView

/**
 * Top Activity Component
 */
class TopActivityComponent : AnkoComponent<TopActivity>, TopView {
    var menuItemClickListener: ((MenuItem) -> Boolean)? = null
        set(value) {
            field = value
            menuNavigation.setOnNavigationItemSelectedListener(value)
        }

    lateinit var menuNavigation: BottomNavigationView

    override fun createView(ui: AnkoContext<TopActivity>): View = with(ui) {
        verticalLayout {
            toolbar {
                owner.setSupportActionBar(this)
            }
            frameLayout {
                id = R.id.layout_container
            }.lparams(matchParent, matchParent, weight = 1F)
            menuNavigation = bottomNavigationView {
                inflateMenu(R.menu.menu_top)
            }
        }
    }

}
