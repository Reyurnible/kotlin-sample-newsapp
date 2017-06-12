package com.github.reyurnible.news.component.scene.top

import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import com.github.reyurnible.news.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.verticalLayout

/**
 * Top Activity Component
 */
class TopActivityComponent : AnkoComponent<TopActivity> {
    var menuItemClickListener: ((MenuItem) -> Boolean)? = null
        set(value) {
            field = value
            menuNavigation.setOnNavigationItemSelectedListener(value)
        }

    lateinit var titleBar: Toolbar
    lateinit var container: FrameLayout
    lateinit var menuNavigation: BottomNavigationView

    override fun createView(ui: AnkoContext<TopActivity>): View = with(ui) {
        verticalLayout {
            titleBar = toolbar {
                owner.setSupportActionBar(this)
            }
            container = frameLayout {

            }.lparams {
                weight = 1F
            }
            menuNavigation = bottomNavigationView {
                inflateMenu(R.menu.menu_top)
            }
        }
    }

}
