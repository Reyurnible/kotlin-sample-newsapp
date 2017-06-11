package com.github.reyurnible.news.repository.response

import com.github.reyurnible.news.entity.Article

/**
 * Get Articles Request Response
 * /v1/articles
 */
data class GetArticlesResponse(
        val status: String,
        val source: String,
        val sortBy: String,
        val articles: List<Article>
)
