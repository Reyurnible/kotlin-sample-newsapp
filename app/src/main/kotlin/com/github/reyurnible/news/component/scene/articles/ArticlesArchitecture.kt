package com.github.reyurnible.news.component.scene.articles

import android.arch.lifecycle.LifecycleObserver
import com.github.reyurnible.news.extension.Variable
import com.github.reyurnible.news.repository.DomainError
import com.github.reyurnible.news.repository.entity.Article
import io.reactivex.Observable

/**
 * Articles Architecture
 */
interface ArticlesPresenter {
    fun onScrollReached(count: Int)

    interface DataHolder {
        val articleList: Variable<MutableList<Article>>
    }
}

interface ArticlesView {
    var articleList: Observable<List<Article>>

    fun bindLifecycle(observer: LifecycleObserver)
    fun showError(error: DomainError)
}
