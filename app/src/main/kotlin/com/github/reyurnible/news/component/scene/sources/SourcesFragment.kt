package com.github.reyurnible.news.component.scene.sources

import com.github.reyurnible.news.entity.Source
import com.trello.rxlifecycle2.components.support.RxFragment
import io.reactivex.Observable

/**
 * Sources Scene
 */
class SourcesFragment : RxFragment(), SourcesView {
    override lateinit var sources: Observable<List<Source>>




}