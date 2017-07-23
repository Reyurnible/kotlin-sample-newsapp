package com.github.reyurnible.news.component.scene.top

/**
 * TopScene Architecture
 * Provide interface for Top Scene
 */
interface TopPresenter {
    val view: TopView

    fun onClickMenuHome()
    fun onClickMenuSources()
    fun onClickMenuFavorites()
}

interface TopView {
    fun showHome()
    fun showSources()
    fun showFavorites()
}
