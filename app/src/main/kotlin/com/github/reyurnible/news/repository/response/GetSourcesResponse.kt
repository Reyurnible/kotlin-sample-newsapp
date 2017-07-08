package com.github.reyurnible.news.repository.response

import com.github.reyurnible.news.entity.ArticleSource

/**
 * Get Sources Request Response
 * /v1/sources
 */
data class GetSourcesResponse(
        val status: String,
        val sources: List<ArticleSource>
)
