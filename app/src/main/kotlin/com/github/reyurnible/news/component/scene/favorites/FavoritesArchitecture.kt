package com.github.reyurnible.news.component.scene.favorites

import android.arch.lifecycle.LifecycleObserver
import com.github.reyurnible.news.extension.Variable
import com.github.reyurnible.news.repository.DomainError
import com.github.reyurnible.news.repository.entity.Article
import io.reactivex.Observable

/**
 * Favorite Articles Architecture
 */
interface FavoritesPresenter {
    // Survive Scene Data
    interface DataHolder {
        val articleList: Variable<MutableList<Article>>
    }
}

interface FavoritesView {
    var articleList: Observable<List<Article>>

    fun bindLifecycle(observer: LifecycleObserver)
    fun showError(error: DomainError)
}
