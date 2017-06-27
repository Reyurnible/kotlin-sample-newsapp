package com.github.reyurnible.news.component.scene.top

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.reyurnible.news.R
import com.github.reyurnible.news.component.scene.home.HomeFragment
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

    private val component: TopActivityComponent = TopActivityComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set Content View
        component.setContentView(this)
        component.menuItemClickListener = { menuItem ->
            when (menuItem.itemId) {
                R.id.top_action_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.layout_container, HomeFragment.createInstance()).commit()
                    true
                }
                R.id.top_action_sources -> true
                R.id.top_action_favorites -> true
                else -> false
            }
        }
    }

    fun setContents() {

    }

}
