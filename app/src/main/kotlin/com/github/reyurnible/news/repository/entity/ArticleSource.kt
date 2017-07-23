package com.github.reyurnible.news.repository.entity

/**
 * Article Source
 */
data class ArticleSource(
        val id: String,
        val name: String,
        val description: String?,
        val url: String?,
        val category: String,
        val language: String,
        val country: String,
        val sortBysAvailable: List<String>
)
