package com.github.reyurnible.news.component.scene.home

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.reyurnible.news.AppBinder
import com.github.reyurnible.news.repository.DomainError
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.FragmentInjector
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.AnkoContext


/**
 * Home Scene
 */
class HomeFragment : RxFragment(), HomeView, FragmentInjector, LifecycleRegistryOwner {
    private object Key {
        // Has no argument
    }

    companion object {
        fun createInstance(): HomeFragment = HomeFragment()
    }

    override lateinit var sourceList: Observable<List<ArticleSource>>

    override val injector: KodeinInjector = KodeinInjector()
    private val registry = LifecycleRegistry(this)
    private val presenter: HomePresenter by injector.instance()
    private val component: HomeFragmentComponent = HomeFragmentComponent()

    override fun provideOverridingModule() = Kodein.Module {
        extend(AppBinder.kodein)
        bind<HomeView>() with instance(this@HomeFragment)
        bind<HomePresenter.HomeSceneDataHolder>() with instance(ViewModelProviders.of(this@HomeFragment).get(HomePresenter.HomeSceneDataHolder::class.java))
        bind<HomePresenter>() with provider {
            HomePresenterImpl(view = instance(), sceneDataHolder = instance(), newsRepository = instance())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View =
            component.createView(AnkoContext.create(activity, this))

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sourceList
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    component.pagerAdapter.sources = it
                    component.pagerAdapter.notifyDataSetChanged()
                }
    }

    override fun onDestroy() {
        destroyInjector()
        super.onDestroy()
    }

    override fun getLifecycle(): LifecycleRegistry = registry

    override fun bindLifecycle(observer: LifecycleObserver) {
        lifecycle.addObserver(observer)
    }

    override fun showError(error: DomainError) {

    }

}
