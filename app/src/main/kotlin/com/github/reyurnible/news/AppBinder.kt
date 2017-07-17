package com.github.reyurnible.news

import android.app.Application
import com.github.reyurnible.news.repository.repositoryModule
import com.github.reyurnible.news.source.sourceModule
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.autoAndroidModule

/**
 * Application DI Binder
 */
object AppBinder : KodeinAware {
    private lateinit var application: Application

    fun initialize(application: Application) {
        this.application = application
    }

    inline fun <reified T : Any> bind(tag: Any? = null) = kodein.Instance<T>(generic(), tag)

    override val kodein: Kodein by Kodein.lazy {
        import(autoAndroidModule(application))
        import(sourceModule)
        import(repositoryModule)
    }

}
