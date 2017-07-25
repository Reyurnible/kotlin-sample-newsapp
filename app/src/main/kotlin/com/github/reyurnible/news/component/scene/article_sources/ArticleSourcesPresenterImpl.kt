package com.github.reyurnible.news.component.scene.article_sources

import com.github.reyurnible.news.AppBinder
import com.github.reyurnible.news.component.scene.RxLifecycleObserver
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.reyurnible.news.repository.NewsRepository
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/**
 * Sources Presenter Impl
 */
class ArticleSourcesPresenterImpl(
        private val view: ArticleSourcesView,
        private val sceneDataHolder: ArticleSourcesPresenter.ArticleSourceSceneDataHolder
) : RxLifecycleObserver(), ArticleSourcesPresenter {

    private var newsRepository: NewsRepository = AppBinder.bind()

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

}
