package com.github.reyurnible.news.component.scene.favorites

import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.component.scene.RxLifecycleObserver
import com.github.reyurnible.news.extension.Variable
import com.github.reyurnible.news.repository.NewsRepository
import com.github.reyurnible.news.repository.entity.Article
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/**
 * Favorite Articles Presenter Impl
 */
class FavoritesPresenterImpl(
        private val view: FavoritesView,
        private val sceneDataHolder: FavoritesPresenter.DataHolder,
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

    class DataHolderImpl : ViewModel(), FavoritesPresenter.DataHolder {
        override val articleList: Variable<MutableList<Article>> = Variable.createDefault(mutableListOf())
    }

}
