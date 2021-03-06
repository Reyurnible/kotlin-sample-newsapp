package com.github.reyurnible.news.component.scene.favorites

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.reyurnible.news.component.scene.alertError
import com.github.reyurnible.news.repository.DomainError
import com.github.reyurnible.news.repository.entity.Article
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.SupportFragmentInjector
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.AnkoContext

/**
 * Favorite Articles Scene
 */
class FavoritesFragment : RxFragment(),
        FavoritesView,
        LifecycleRegistryOwner,
        SupportFragmentInjector,
        FavoritesFragmentComponent.SourcesFragmentComponentListener {
    private object Key {

    }

    companion object {
        fun createInstance(): FavoritesFragment = FavoritesFragment()
    }

    // Kodein
    override val injector: KodeinInjector = KodeinInjector()
    private val presenter: FavoritesPresenter by injector.instance()
    // Presenter Datas
    override lateinit var articleList: Observable<List<Article>>

    private val registry = LifecycleRegistry(this)
    private val component: FavoritesFragmentComponent = FavoritesFragmentComponent(this)

    override fun provideOverridingModule() = Kodein.Module {
        bind<FavoritesView>() with instance(this@FavoritesFragment)
        bind<FavoritesPresenter.DataHolder>() with instance(
                ViewModelProviders.of(this@FavoritesFragment).get(FavoritesPresenterImpl.DataHolderImpl::class.java)
        )
        // Inject Presenter
        bind<FavoritesPresenter>() with provider {
            FavoritesPresenterImpl(
                    view = instance(),
                    sceneDataHolder = instance(),
                    newsRepository = instance()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            component.createView(AnkoContext.create(activity, this))

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleList
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    component.adapter.apply {
                        articles = it
                        notifyDataSetChanged()
                    }
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
        alertError(error)
    }

    override fun onScrollReached(count: Int) {

    }
}
