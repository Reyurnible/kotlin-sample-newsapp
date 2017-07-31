package com.github.reyurnible.news.component.scene.article_sources

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.reyurnible.news.AppBinder
import com.github.reyurnible.news.component.scene.articles.*
import com.github.reyurnible.news.extension.start
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
 * Sources Scene
 */
class ArticleSourcesFragment : RxFragment(),
        ArticleSourcesView,
        LifecycleRegistryOwner,
        FragmentInjector,
        ArticleSourcesFragmentComponent.SourcesFragmentComponentListener {
    companion object {
        fun createInstance(): ArticleSourcesFragment = ArticleSourcesFragment()
    }

    override val injector: KodeinInjector = KodeinInjector()
    private lateinit var presenter: ArticleSourcesPresenter

    override lateinit var articleSourceList: Observable<List<ArticleSource>>

    private val registry = LifecycleRegistry(this)
    private val component: ArticleSourcesFragmentComponent = ArticleSourcesFragmentComponent(this)

    override fun provideOverridingModule() = Kodein.Module {
        extend(AppBinder.kodein)
        bind<ArticleSourcesView>() with instance(this@ArticleSourcesFragment)
        bind<ArticleSourcesPresenter.ArticleSourceSceneDataHolder>() with instance(
                ViewModelProviders.of(this@ArticleSourcesFragment).get(ArticleSourcesPresenter.ArticleSourceSceneDataHolder::class.java)
        )
        // Inject Presenter
        bind<ArticleSourcesPresenter>() with provider {
            ArticleSourcesPresenterImpl(
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
        // Add Listener
        component.adapter.onItemClickListener = presenter::onClickSource
        articleSourceList
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe { sources ->
                    component.adapter.run {
                        this.sources = sources
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onScrollReached(count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun moveToArticles(source: ArticleSource) {
        ArticlesActivity.createIntent(activity, sourceId = source.id).start(activity)
    }

}
