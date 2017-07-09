package com.github.reyurnible.news.component.scene.favorites

import com.github.reyurnible.news.AppBinder
import com.github.reyurnible.news.component.scene.RxLifecycleObserver
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.reyurnible.news.repository.NewsRepository
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/**
 * Favorite Articles Presenter Impl
 */
class FavoritesPresenterImpl(
        private val view: FavoritesView,
        private val sceneDataHolder: FavoritesPresenter.FavoriteSceneDataHolder
) : RxLifecycleObserver(), FavoritesPresenter {

    private var newsRepository: NewsRepository = AppBinder.bind()

    init {
        // Observe parent lifecycle
        view.bindLifecycle(this)
        view.sourceList = sceneDataHolder.sourceList.asObservable().map { it.toList() }
    }

    override fun onCreate() {
        super.onCreate()
        newsRepository.getSources()
                .bindToLifecycle(this)
                .subscribe({
                    sceneDataHolder.sourceList.value = it.toMutableList()
                }, {

                })
    }

    override fun onClickSource(source: ArticleSource) {
        view.moveToArticles(source)
    }

}
