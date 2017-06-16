package com.github.reyurnible.news.component.scene.top

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.reyurnible.news.R
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.jetbrains.anko.setContentView

class TopActivity : RxAppCompatActivity() {
    private object Key {
        // Has no argument
    }

    companion object {
        fun createIntent(context: Context): Intent =
                Intent(context, TopActivity::class.java)
    }

    lateinit var component: TopActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set Content View
        component = TopActivityComponent()
        component.setContentView(this)
        component.menuItemClickListener = { menuItem ->
            when (menuItem.itemId) {
                R.id.top_action_home -> true
                R.id.top_action_sources -> true
                R.id.top_action_favorites -> true
                else -> false
            }
        }
    }

}
