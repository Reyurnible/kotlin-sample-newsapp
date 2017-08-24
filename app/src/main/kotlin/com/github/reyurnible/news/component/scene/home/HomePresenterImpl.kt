package com.github.reyurnible.news.component.scene.home

import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.component.scene.RxLifecycleObserver
import com.github.reyurnible.news.extension.Variable
import com.github.reyurnible.news.repository.NewsRepository
import com.github.reyurnible.news.repository.entity.ArticleSource
import com.github.reyurnible.news.repository.parseError
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/**
 * Home Presenter Impl
 */
class HomePresenterImpl(
        private val view: HomeView,
        private val sceneDataHolder: HomePresenter.DataHolder,
        protected val newsRepository: NewsRepository
) : RxLifecycleObserver(), HomePresenter {

    init {
        // Observe parent lifecycle
        view.bindLifecycle(this)
        view.sourceList = sceneDataHolder.sourceList.asObservable().map { it.toList() }
    }

    override fun onCreate() {
        super.onCreate()
        newsRepository.getSources()
                .bindToLifecycle(this)
                .subscribe({
                    sceneDataHolder.sourceList.value = it.toMutableList()
                }, {
                    view.showError(parseError(it))
                })
    }

    class DataHolderImpl : HomePresenter.DataHolder, ViewModel() {
        override val sourceList: Variable<MutableList<ArticleSource>> = Variable.createDefault(mutableListOf())
    }

}
