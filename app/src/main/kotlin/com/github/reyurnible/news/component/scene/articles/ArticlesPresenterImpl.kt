package com.github.reyurnible.news.component.scene.articles

import com.github.reyurnible.news.component.scene.RxLifecycleObserver
import com.github.reyurnible.news.repository.NewsRepository
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

class ArticlesPresenterImpl(
        private val view: ArticlesView,
        private val sourceId: String,
        private val sceneDataHolder: ArticlesPresenter.ArticlesSceneDataHolder,
        private val newsRepository: NewsRepository
) : RxLifecycleObserver(), ArticlesPresenter {

    init {
        view.bindLifecycle(this)
        view.articleList = sceneDataHolder.articleList.asObservable().map { it.toList() }
    }

    override fun onCreate() {
        super.onCreate()
        newsRepository.getArticles(sourceId)
                .bindToLifecycle(this)
                .subscribe({
                    sceneDataHolder.articleList.value = it.toMutableList()
                }, {

                })
    }

    override fun onScrollReached(count: Int) {

    }
}
