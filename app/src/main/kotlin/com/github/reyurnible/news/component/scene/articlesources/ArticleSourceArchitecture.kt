package com.github.reyurnible.news.component.scene.articlesources

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.entity.ArticleSource
import com.github.reyurnible.news.extension.Variable
import io.reactivex.Observable

/**
 * Sources Architecture
 */
interface SourcesPresenter {
    fun onClickSource(source: ArticleSource)
}

interface SourcesView {
    var sourceList: Observable<List<ArticleSource>>

    fun bindLifecycle(observer: LifecycleObserver)
    fun showError()

    fun moveToArticles(source: ArticleSource)
}

// Survive Scene Data
class SourceSceneDataHolder : ViewModel() {
    val sourceList: Variable<MutableList<ArticleSource>> = Variable.createDefault(mutableListOf())
}
