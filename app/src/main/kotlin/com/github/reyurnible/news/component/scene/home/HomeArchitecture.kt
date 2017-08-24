package com.github.reyurnible.news.component.scene.home

import android.arch.lifecycle.LifecycleObserver
import com.github.reyurnible.news.extension.Variable
import com.github.reyurnible.news.repository.DomainError
import com.github.reyurnible.news.repository.entity.ArticleSource
import io.reactivex.Observable

/**
 * Home Architecture
 */
interface HomePresenter {
    fun onCreate()

    // Survive Scene Data
    interface DataHolder {
        val sourceList: Variable<MutableList<ArticleSource>>
    }
}

interface HomeView {
    var sourceList: Observable<List<ArticleSource>>

    fun bindLifecycle(observer: LifecycleObserver)
    fun showError(error: DomainError)
}
