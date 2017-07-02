package com.github.reyurnible.news.component.scene.sources

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.entity.Source
import com.github.reyurnible.news.extension.Variable
import io.reactivex.Observable

/**
 * Sources Architecture
 */
interface SourcesPresenter {

}

interface SourcesView {
    var sourceList: Observable<List<Source>>
    fun bindLifecycle(observer: LifecycleObserver)
    fun showError()
}

// Survive Scene Data
class SourceSceneDataHolder : ViewModel() {
    val sourceList: Variable<MutableList<Source>> = Variable.createDefault(mutableListOf())
}
