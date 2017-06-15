package com.github.reyurnible.news.component.scene.top

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.jetbrains.anko.setContentView

class TopActivity : RxAppCompatActivity() {
    lateinit var component: TopActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set Content View
        component = TopActivityComponent()
        component.setContentView(this)
        component.menuItemClickListener = { menuItem ->
            when (menuItem.itemId) {
                else -> false
            }
        }
    }
}
