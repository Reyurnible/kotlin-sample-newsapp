package com.github.reyurnible.news.source.local_requery.dto

import android.os.Parcelable
import io.requery.*
import java.util.*

/**
 * Article Data Access Object
 */
@Entity
interface Article : Persistable, Parcelable {
    @get:Key
    @get:Column(unique = true)
    @get:Index("title_index")
    var title: String
    @get:Index("author_index")
    var author: String
    var description: String
    var url: String
    var urlToImage: String
    var publishedAt: Date
}
