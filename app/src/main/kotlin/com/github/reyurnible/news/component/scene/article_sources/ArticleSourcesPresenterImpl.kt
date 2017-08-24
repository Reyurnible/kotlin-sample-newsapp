package com.github.reyurnible.news.component.scene.article_sources

import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.component.scene.RxLifecycleObserver
import com.github.reyurnible.news.extension.Variable
import com.github.reyurnible.news.repository.NewsRepository
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/**
 * Sources Presenter Impl
 */
class ArticleSourcesPresenterImpl(
        private val view: ArticleSourcesView,
        private val sceneDataHolder: ArticleSourcesPresenter.DataHolder,
        private val newsRepository: NewsRepository
) : RxLifecycleObserver(), ArticleSourcesPresenter {

    init {
        // Observe parent lifecycle
        view.bindLifecycle(this)
        view.articleSourceList = sceneDataHolder.articleSourceList.asObservable().map { it.toList() }
    }

    override fun onCreate() {
        super.onCreate()
        newsRepository.getSources()
                .bindToLifecycle(this)
                .subscribe({
                    sceneDataHolder.articleSourceList.value = it.toMutableList()
                }, {

                })
    }

    override fun onClickSource(source: ArticleSource) {
        view.moveToArticles(source)
    }

    // Survive Scene Data
    class DataHolderImpl : ViewModel(), ArticleSourcesPresenter.DataHolder {
        override val articleSourceList: Variable<MutableList<ArticleSource>> = Variable.createDefault(mutableListOf())
    }

}
