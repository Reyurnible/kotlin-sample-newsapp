package com.github.reyurnible.news.component.scene.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment
import org.jetbrains.anko.AnkoContext

/**
 * Home Scene
 */
class HomeFragment : RxFragment() {
    private object Key {
        // Has no argument
    }

    companion object {
        fun createInstance(): HomeFragment = HomeFragment()
    }

    private val component: HomeFragmentComponent = HomeFragmentComponent()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            component.createView(AnkoContext.Companion.create<HomeFragment>(activity, this))

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



}
