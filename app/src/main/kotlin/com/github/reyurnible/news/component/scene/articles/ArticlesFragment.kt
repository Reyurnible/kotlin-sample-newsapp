package com.github.reyurnible.news.component.scene.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.reyurnible.news.AppBinder
import com.github.reyurnible.news.extension.applyArguments
import com.github.reyurnible.news.repository.NewsRepository
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoContext

/**
 * Articles Scene
 */
class ArticlesFragment : RxFragment(), ArticlesFragmentComponent.ArticlesFragmentComponentListener {
    private object Key {

        const val sourceId = "sourceId"
    }

    companion object {

        fun createInstance(sourceId: String): ArticlesFragment = ArticlesFragment().applyArguments {
            putString(Key.sourceId, sourceId)
        }
    }

    // Repository
    private var newsRepository: NewsRepository = AppBinder.bind()

    private val component: ArticlesFragmentComponent = ArticlesFragmentComponent()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            component.createView(AnkoContext.create(activity, this))

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsRepository.getArticles(arguments.getString(Key.sourceId))
                .subscribeOn(Schedulers.io())
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
}