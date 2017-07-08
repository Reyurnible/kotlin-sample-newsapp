package com.github.reyurnible.news.component.scene.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.github.reyurnible.news.component.scene.articles.ArticlesFragment
import com.github.reyurnible.news.entity.ArticleSource

/**
 * Home Scene Pager Adapter
 */
class HomePagerAdapter(
        fragmentManager: FragmentManager,
        var sources: List<ArticleSource>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = ArticlesFragment.createInstance(sources[position].id)

    override fun getCount(): Int = sources.size

    override fun getPageTitle(position: Int): CharSequence = sources[position].name
}
