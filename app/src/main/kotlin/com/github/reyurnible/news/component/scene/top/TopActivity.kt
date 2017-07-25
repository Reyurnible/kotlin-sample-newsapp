package com.github.reyurnible.news.component.scene.top

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.reyurnible.news.AppBinder
import com.github.reyurnible.news.R
import com.github.reyurnible.news.component.scene.article_sources.ArticleSourcesFragment
import com.github.reyurnible.news.component.scene.favorites.FavoritesFragment
import com.github.reyurnible.news.component.scene.home.HomeFragment
import com.github.reyurnible.news.extension.setContentFragment
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.ActivityInjector
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.jetbrains.anko.setContentView

class TopActivity : RxAppCompatActivity(), TopView, ActivityInjector {
    private object Key {
        // Has no argument
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, TopActivity::class.java)
    }

    override val injector: KodeinInjector = KodeinInjector()
    private val presenter: TopPresenter by injector.instance()

    private val component: TopActivityComponent = TopActivityComponent()

    override fun provideOverridingModule() = Kodein.Module {
        extend(AppBinder.kodein)
        bind<TopView>() with instance(this@TopActivity)
        bind<TopPresenter>() with provider {
            TopPresenterImpl(view = instance())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
        // Set Content View
        component.setContentView(this)
        component.menuItemClickListener = { menuItem ->
            when (menuItem.itemId) {
                R.id.top_action_home -> {
                    presenter.onClickMenuHome()
                    true
                }
                R.id.top_action_sources -> {
                    presenter.onClickMenuSources()
                    true
                }
                R.id.top_action_favorites -> {
                    presenter.onClickMenuFavorites()
                    true
                }
                else -> false
            }
        }
        component.menuNavigation.selectedItemId = R.id.top_action_home
    }

    override fun onDestroy() {
        destroyInjector()
        super.onDestroy()
    }

    override fun showHome() {
        setContentFragment(fragment = HomeFragment.createInstance())
    }

    override fun showSources() {
        setContentFragment(fragment = ArticleSourcesFragment.createInstance())
    }

    override fun showFavorites() {
        setContentFragment(fragment = FavoritesFragment.createInstance())
    }


}
