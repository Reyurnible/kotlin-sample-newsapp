package com.github.reyurnible.news.component.scene.articles

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.reyurnible.news.AppBinder
import com.github.reyurnible.news.extension.applyArguments
import com.github.reyurnible.news.repository.entity.Article
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.FragmentInjector
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.AnkoContext

/**
 * Articles Scene
 */
class ArticlesFragment : RxFragment(),
        ArticlesView,
        LifecycleRegistryOwner,
        FragmentInjector,
        ArticlesFragmentComponent.ArticlesFragmentComponentListener {
    private object Key {
        const val sourceId = "sourceId"
    }

    companion object {
        fun createInstance(sourceId: String): ArticlesFragment = ArticlesFragment().applyArguments {
            putString(Key.sourceId, sourceId)
        }
    }

    // Kodein
    override val injector: KodeinInjector = KodeinInjector()
    private val presenter: ArticlesPresenter by injector.instance()
    // Presenter Datas
    override lateinit var articleList: Observable<List<Article>>

    private val registry = LifecycleRegistry(this)
    private val component: ArticlesFragmentComponent = ArticlesFragmentComponent()

    override fun provideOverridingModule() = Kodein.Module {
        extend(AppBinder.kodein)
        bind<ArticlesView>() with instance(this@ArticlesFragment)
        bind<ArticlesPresenter.ArticlesSceneDataHolder>() with instance(
                ViewModelProviders.of(this@ArticlesFragment).get(ArticlesPresenter.ArticlesSceneDataHolder::class.java)
        )
        bind<String>(tag = Key.sourceId) with instance(arguments.getString(Key.sourceId))
        // Inject Presenter
        bind<ArticlesPresenter>() with provider {
            ArticlesPresenterImpl(
                    view = instance(),
                    sourceId = instance(Key.sourceId),
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
                .subscribe({
                    component.adapter.apply {
                        articles = it
                        notifyDataSetChanged()
                    }
                }, {
                    it.printStackTrace()
                })
    }

    override fun onDestroy() {
        destroyInjector()
        super.onDestroy()
    }

    override fun getLifecycle(): LifecycleRegistry = registry

    override fun bindLifecycle(observer: LifecycleObserver) {
        lifecycle.addObserver(observer)
    }

    override fun showError() {

    }

    override fun onScrollReached(count: Int) {
        presenter.onScrollReached(count)
    }
}
