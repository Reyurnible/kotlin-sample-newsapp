package com.github.reyurnible.news.component.scene.sources

import com.github.reyurnible.news.AppBinder
import com.github.reyurnible.news.component.scene.RxLifecycleObserver
import com.github.reyurnible.news.entity.Source
import com.github.reyurnible.news.repository.NewsRepository
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/**
 * Sources Presenter Impl
 */
class SourcesPresenterImpl(
        private val view: SourcesView,
        private val sceneDataHolder: SourceSceneDataHolder
) : RxLifecycleObserver(), SourcesPresenter {

    private var newsRepository: NewsRepository = AppBinder.bind()

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

                })
    }

    override fun onClickSource(source: Source) {
        view.moveToArticles(source)
    }

}
