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
import com.github.reyurnible.news.entity.ArticleSource
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.AnkoContext

/**
 * Sources Scene
 */
class ArticleArticleSourcesFragment : RxFragment(), ArticleSourcesView, LifecycleRegistryOwner, ArticleSourcesFragmentComponent.SourcesFragmentComponentListener {
    private object Key {

    }

    companion object {
        fun createInstance(): ArticleArticleSourcesFragment = ArticleArticleSourcesFragment()
    }

    override lateinit var articleSourceList: Observable<List<ArticleSource>>

    private val registry = LifecycleRegistry(this)
    private lateinit var presenterArticle: ArticleSourcesPresenter
    private val component: ArticleSourcesFragmentComponent = ArticleSourcesFragmentComponent(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenterArticle = ArticleSourcesPresenterImpl(
                view = this,
                sceneDataHolder = ViewModelProviders.of(this).get(ArticleSourcesPresenter.ArticleSourceSceneDataHolder::class.java)
        )
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            component.createView(AnkoContext.create(activity, this))

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleSourceList
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    component.adapter.sources.clear()
                    component.adapter.sources.addAll(it)
                    component.adapter.notifyDataSetChanged()
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

    }

}
