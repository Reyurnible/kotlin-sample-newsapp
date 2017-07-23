package com.github.reyurnible.news.component.scene.articles

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.extension.Variable
import com.github.reyurnible.news.repository.entity.Article
import io.reactivex.Observable

/**
 * Articles Architecture
 */
interface ArticlesPresenter {
    val view: ArticlesView
    val sourceId: String

    fun onScrollReached(count: Int)

    class ArticlesSceneDataHolder : ViewModel() {
        val articleList: Variable<MutableList<Article>> = Variable.createDefault(mutableListOf())

    }
}

interface ArticlesView {
    var articleList: Observable<List<Article>>

    fun bindLifecycle(observer: LifecycleObserver)
    fun showError()
}
