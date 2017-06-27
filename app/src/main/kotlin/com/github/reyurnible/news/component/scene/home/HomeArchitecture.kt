package com.github.reyurnible.news.component.scene.home

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.github.reyurnible.news.entity.Source
import com.github.reyurnible.news.extension.Variable
import io.reactivex.Observable

/**
 * Home Architecture
 */
interface HomePresenter {

}

interface HomeView {
    var sourceList: Observable<List<Source>>
    fun bindLifecycle(observer: LifecycleObserver)
    fun showError()
}

// Survive Scene Data
class HomeSceneDataHolder : ViewModel() {
    val sourceList: Variable<MutableList<Source>> = Variable.createDefault(mutableListOf())
}
