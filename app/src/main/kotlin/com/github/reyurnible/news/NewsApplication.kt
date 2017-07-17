package com.github.reyurnible.news

import android.app.Application
import com.github.reyurnible.news.source.local_requery.RequeryClient

/**
 * NewsApplication
 */
class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppBinder.initialize(this)
        // Database initialize
        RequeryClient.initialize(this)
    }
}
