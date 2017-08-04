package com.github.reyurnible.news

import android.app.Application
import com.github.reyurnible.news.repository.repositoryModule
import com.github.reyurnible.news.source.local_requery.RequeryClient
import com.github.reyurnible.news.source.sourceModule
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.android.autoAndroidModule
import com.github.salomonbrys.kodein.generic
import com.github.salomonbrys.kodein.lazy

/**
 * NewsApplication
 */
class NewsApplication : Application(), KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(autoAndroidModule(this@NewsApplication))

        import(applicationModule(this@NewsApplication))

        import(sourceModule)
        import(repositoryModule)
    }

    override fun onCreate() {
        super.onCreate()
        // Database initialize
        RequeryClient.initialize(this)
    }

    inline fun <reified T : Any> bind(tag: Any? = null) = kodein.Instance<T>(generic(), tag)
}
