package com.github.reyurnible.news.component.scene.sources

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.reyurnible.news.entity.Source
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.AnkoContext
import java.util.ArrayList

/**
 * Sources Scene
 */
class SourcesFragment : RxFragment(), SourcesView, LifecycleRegistryOwner, SourcesFragmentComponent.SourcesFragmentComponentListener {
    private object Key {

    }

    companion object {
        fun createInstance(): SourcesFragment = SourcesFragment()
    }

    override lateinit var sourceList: Observable<List<Source>>

    private val registry = LifecycleRegistry(this)
    private lateinit var presenter: SourcesPresenter
    private val component: SourcesFragmentComponent = SourcesFragmentComponent(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = SourcesPresenterImpl(
                view = this,
                sceneDataHolder = ViewModelProviders.of(this).get(SourceSceneDataHolder::class.java)
        )
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            component.createView(AnkoContext.create(activity, this))

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sourceList
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

    override fun moveToArticles(source: Source) {

    }

}