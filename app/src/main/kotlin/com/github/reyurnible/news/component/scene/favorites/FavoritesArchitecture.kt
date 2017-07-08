package com.github.reyurnible.news.component.scene.favorites

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.entity.ArticleSource
import com.github.reyurnible.news.extension.Variable
import io.reactivex.Observable

/**
 * Favorite Articles Architecture
 */
interface FavoritesPresenter {
    fun onClickSource(source: ArticleSource)

    // Survive Scene Data
    class FavoriteSceneDataHolder : ViewModel() {
        val sourceList: Variable<MutableList<ArticleSource>> = Variable.createDefault(mutableListOf())
    }
}

interface FavoritesView {
    var sourceList: Observable<List<ArticleSource>>

    fun bindLifecycle(observer: LifecycleObserver)
    fun showError()

    fun moveToArticles(source: ArticleSource)
}
