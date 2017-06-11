package com.github.reyurnible.news.repository.response

import com.github.reyurnible.news.entity.Source

/**
 * Get Sources Request Response
 * /v1/sources
 */
data class GetSourcesResponse(
        val status: String,
        val sources: List<Source>
)
