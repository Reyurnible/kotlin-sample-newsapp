package com.github.reyurnible.news.component.scene.articlesources

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.reyurnible.news.extension.Variable
import io.reactivex.Observable

/**
 * Sources Architecture
 */
interface ArticleSourcesPresenter {
    fun onClickSource(source: ArticleSource)

    // Survive Scene Data
    data class ArticleSourceSceneDataHolder(
            val articleSourceList: Variable<MutableList<ArticleSource>> = Variable.createDefault(mutableListOf())
    ) : ViewModel()
}

interface ArticleSourcesView {
    var articleSourceList: Observable<List<ArticleSource>>

    fun bindLifecycle(observer: LifecycleObserver)
    fun showError()

    fun moveToArticles(source: ArticleSource)
}
