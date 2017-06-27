package com.github.reyurnible.news.component.scene.sources

import com.github.reyurnible.news.entity.Source
import io.reactivex.Observable

/**
 * Sources Architecture
 */
interface SourcesPresenter {

}

interface SourcesView {
    var sources: Observable<List<Source>>
}
