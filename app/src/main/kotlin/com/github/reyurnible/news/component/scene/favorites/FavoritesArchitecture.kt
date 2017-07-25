package com.github.reyurnible.news.component.scene.favorites

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.extension.Variable
import com.github.reyurnible.news.repository.entity.Article
import io.reactivex.Observable

/**
 * Favorite Articles Architecture
 */
interface FavoritesPresenter {
    // Survive Scene Data
    class FavoriteSceneDataHolder : ViewModel() {
        val articleList: Variable<MutableList<Article>> = Variable.createDefault(mutableListOf())
    }
}

interface FavoritesView {
    var articleList: Observable<List<Article>>

    fun bindLifecycle(observer: LifecycleObserver)
    fun showError()
}
