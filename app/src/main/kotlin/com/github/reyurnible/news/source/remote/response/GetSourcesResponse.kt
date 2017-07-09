package com.github.reyurnible.news.source.remote.response

import com.github.reyurnible.news.repository.entity.ArticleSource

/**
 * Get Sources Request Response
 * /v1/sources
 */
data class GetSourcesResponse(
        val status: String,
        val sources: List<ArticleSource>
)
