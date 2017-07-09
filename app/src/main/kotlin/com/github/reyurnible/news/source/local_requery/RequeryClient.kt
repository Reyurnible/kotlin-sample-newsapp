package com.github.reyurnible.news.source.local_requery

import android.app.Application
import com.github.reyurnible.news.BuildConfig
import com.github.reyurnible.news.source.local_requery.dao.Models
import io.requery.Persistable
import io.requery.android.sqlite.DatabaseSource
import io.requery.reactivex.ReactiveEntityStore
import io.requery.reactivex.ReactiveSupport
import io.requery.sql.EntityDataStore
import io.requery.sql.KotlinConfiguration
import io.requery.sql.KotlinEntityDataStore
import io.requery.sql.TableCreationMode


/**
 * Requery Database Client
 */
object RequeryClient {
    lateinit var dataStore: ReactiveEntityStore<Persistable>
        private set

    fun initialize(application: Application) {
        // override onUpgrade to handle migrating to a new version
        val source = DatabaseSource(application, Models.DEFAULT, 1)
        if (BuildConfig.DEBUG) {
            // use this in development mode to drop and recreate the tables on every upgrade
            source.setTableCreationMode(TableCreationMode.DROP_CREATE)
        }
        val configuration = source.configuration
        ReactiveSupport.toReactiveStore(EntityDataStore<Persistable>(configuration))
    }

}
