package com.github.reyurnible.news.component.scene.home

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.extension.Variable
import com.github.reyurnible.news.repository.DomainError
import com.github.reyurnible.news.repository.entity.ArticleSource
import io.reactivex.Observable

/**
 * Home Architecture
 */
interface HomePresenter {
    // Survive Scene Data
    class HomeSceneDataHolder : ViewModel() {
        val sourceList: Variable<MutableList<ArticleSource>> = Variable.createDefault(mutableListOf())
    }
}

interface HomeView {
    var sourceList: Observable<List<ArticleSource>>

    fun bindLifecycle(observer: LifecycleObserver)
    fun showError(error: DomainError)
}
