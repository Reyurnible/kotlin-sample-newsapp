package com.github.reyurnible.news.entity

import com.google.gson.annotations.SerializedName

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
        @SerializedName("urlsToLogos")
        val logoUrls: LogoUrl?,
        val sortBysAvailable: List<String>
) {
    class LogoUrl(
            val small: String,
            val medium: String,
            val large: String
    )
}
