package com.github.reyurnible.news.entity

import java.util.*

/**
 * Article
 */
data class Article(
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: Date
)
