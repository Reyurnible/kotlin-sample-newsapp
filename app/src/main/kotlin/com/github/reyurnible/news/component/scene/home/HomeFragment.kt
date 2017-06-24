package com.github.reyurnible.news.component.scene.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.reyurnible.news.AppComponents
import com.github.reyurnible.news.repository.NewsRepository
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
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

    // Repository
    private var newsRepository: NewsRepository = AppComponents.bind()

    private val component: HomeFragmentComponent = HomeFragmentComponent()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            component.createView(AnkoContext.Companion.create<HomeFragment>(activity, this))

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsRepository.getSources()
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe({
                    component.pagerAdapter.apply {
                        sources = it
                        notifyDataSetChanged()
                    }
                }, Throwable::printStackTrace)
    }


}
