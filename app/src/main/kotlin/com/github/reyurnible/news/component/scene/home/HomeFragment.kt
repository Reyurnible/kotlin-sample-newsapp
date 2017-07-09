package com.github.reyurnible.news.component.scene.home

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.AnkoContext


/**
 * Home Scene
 */
class HomeFragment : RxFragment(), HomeView, LifecycleRegistryOwner {
    private object Key {
        // Has no argument
    }

    companion object {
        fun createInstance(): HomeFragment = HomeFragment()
    }

    override lateinit var sourceList: Observable<List<ArticleSource>>

    private val registry = LifecycleRegistry(this)
    private lateinit var presenter: HomePresenter
    private val component: HomeFragmentComponent = HomeFragmentComponent()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = HomePresenterImpl(
                view = this,
                sceneDataHolder = ViewModelProviders.of(this).get(HomePresenter.HomeSceneDataHolder::class.java)
        )
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View =
            component.createView(AnkoContext.create(activity, this))

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sourceList
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    Log.d(HomeFragment::class.java.simpleName, "articleSourceList subscribe: ${it}")
                    component.pagerAdapter.sources = it
                    component.pagerAdapter.notifyDataSetChanged()
                }
    }

    override fun getLifecycle(): LifecycleRegistry = registry

    override fun bindLifecycle(observer: LifecycleObserver) {
        lifecycle.addObserver(observer)
    }

    override fun showError() {

    }

}
