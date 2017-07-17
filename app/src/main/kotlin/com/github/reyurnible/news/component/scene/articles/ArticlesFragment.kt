package com.github.reyurnible.news.component.scene.articles

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.reyurnible.news.extension.applyArguments
import com.github.reyurnible.news.repository.entity.Article
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
        ArticlesFragmentComponent.ArticlesFragmentComponentListener {
    private object Key {
        const val sourceId = "sourceId"
    }

    companion object {
        fun createInstance(sourceId: String): ArticlesFragment = ArticlesFragment().applyArguments {
            putString(Key.sourceId, sourceId)
        }
    }

    override lateinit var articleList: Observable<List<Article>>

    private val registry = LifecycleRegistry(this)
    private lateinit var presenter: ArticlesPresenter
    private val component: ArticlesFragmentComponent = ArticlesFragmentComponent()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = ArticlesPresenterImpl(
                view = this,
                sceneDataHolder = ViewModelProviders.of(this).get(ArticlesPresenter.ArticlesSceneDataHolder::class.java)
        ).apply {
            sourceId = arguments.getString(Key.sourceId)
        }
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
                        articles.addAll(it)
                        notifyDataSetChanged()
                    }
                }, {
                    it.printStackTrace()
                })
    }

    override fun onScrollReached(count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLifecycle(): LifecycleRegistry = registry

    override fun bindLifecycle(observer: LifecycleObserver) {
        lifecycle.addObserver(observer)
    }

    override fun showError() {

    }

}
