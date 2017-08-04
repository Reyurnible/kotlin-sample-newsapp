package com.github.reyurnible.news.component.scene.home

import com.github.reyurnible.news.component.scene.RxLifecycleObserver
import com.github.reyurnible.news.repository.DomainError
import com.github.reyurnible.news.repository.NewsRepository
import com.github.reyurnible.news.repository.parseError
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/**
 * Home Presenter Impl
 */
class HomePresenterImpl(
        private val view: HomeView,
        private val sceneDataHolder: HomePresenter.HomeSceneDataHolder,
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

}
