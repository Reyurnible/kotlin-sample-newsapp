package com.github.reyurnible.news.component.scene.articlesources

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.reyurnible.news.component.scene.articles.ArticlesActivity
import com.github.reyurnible.news.extension.start
import com.github.reyurnible.news.repository.entity.ArticleSource
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
        ArticleSourcesFragmentComponent.SourcesFragmentComponentListener {
    companion object {
        fun createInstance(): ArticleSourcesFragment = ArticleSourcesFragment()
    }

    override lateinit var articleSourceList: Observable<List<ArticleSource>>

    private val registry = LifecycleRegistry(this)
    private lateinit var presenter: ArticleSourcesPresenter
    private val component: ArticleSourcesFragmentComponent = ArticleSourcesFragmentComponent(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = ArticleSourcesPresenterImpl(
                view = this,
                sceneDataHolder = ViewModelProviders.of(this).get(ArticleSourcesPresenter.ArticleSourceSceneDataHolder::class.java)
        )
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

    override fun getLifecycle(): LifecycleRegistry = registry

    override fun bindLifecycle(observer: LifecycleObserver) {
        lifecycle.addObserver(observer)
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onScrollReached(count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun moveToArticles(source: ArticleSource) {
        ArticlesActivity.createIntent(activity, sourceId = source.id).start(activity)
    }

}
