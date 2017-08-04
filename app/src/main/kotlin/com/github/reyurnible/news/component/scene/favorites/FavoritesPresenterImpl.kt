package com.github.reyurnible.news.component.scene.favorites

import com.github.reyurnible.news.component.scene.RxLifecycleObserver
import com.github.reyurnible.news.repository.NewsRepository
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/**
 * Favorite Articles Presenter Impl
 */
class FavoritesPresenterImpl(
        private val view: FavoritesView,
        private val sceneDataHolder: FavoritesPresenter.FavoriteSceneDataHolder,
        private val newsRepository: NewsRepository
) : RxLifecycleObserver(), FavoritesPresenter {

    init {
        // Observe parent lifecycle
        view.bindLifecycle(this)
        view.articleList = sceneDataHolder.articleList.asObservable().map { it.toList() }
    }

    override fun onCreate() {
        super.onCreate()
        newsRepository.getFavoriteArticles()
                .bindToLifecycle(this)
                .subscribe({
                    sceneDataHolder.articleList.value = it.toMutableList()
                }, {

                })
    }

}
