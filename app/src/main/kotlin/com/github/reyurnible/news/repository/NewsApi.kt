package com.github.reyurnible.news.repository

import com.github.reyurnible.news.repository.response.GetArticlesResponse
import com.github.reyurnible.news.repository.response.GetSourcesResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * News Api Retrofit Service
 * API URL : https://newsapi.org/
 */
interface NewsApi {

    @GET("/v1/articles")
    fun getArticles(
            @Query("source") source: String,
            @Query("apiKey") apiKey: String,
            @Query("sortBy") sortBy: String?
    ): Single<GetArticlesResponse>

    @GET("/v1/sources")
    fun getSources(
            @Query("category") category: String?,
            @Query("language") language: String?,
            @Query("country") country: String?
    ): Single<GetSourcesResponse>

}
