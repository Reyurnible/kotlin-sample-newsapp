package com.github.reyurnible.news.component.scene.top

/**
 * Top Presenter Impl
 */
class TopPresenterImpl(
        val view: TopView
) : TopPresenter {
    override fun onClickMenuHome() {
        view.showHome()
    }

    override fun onClickMenuSources() {
        view.showSources()
    }

    override fun onClickMenuFavorites() {
        view.showFavorites()
    }

}
