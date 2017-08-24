package com.github.reyurnible.news.component.scene.article_sources

import android.arch.lifecycle.LifecycleObserver
import com.github.reyurnible.news.extension.Variable
import com.github.reyurnible.news.repository.DomainError
import com.github.reyurnible.news.repository.entity.ArticleSource
import io.reactivex.Observable

/**
 * Sources Architecture
 */
interface ArticleSourcesPresenter {
    fun onClickSource(source: ArticleSource)

    // Survive Scene Data
    interface DataHolder {
        val articleSourceList: Variable<MutableList<ArticleSource>>
    }
}

interface ArticleSourcesView {
    var articleSourceList: Observable<List<ArticleSource>>

    fun bindLifecycle(observer: LifecycleObserver)
    fun showError(error: DomainError)

    fun moveToArticles(source: ArticleSource)
}
