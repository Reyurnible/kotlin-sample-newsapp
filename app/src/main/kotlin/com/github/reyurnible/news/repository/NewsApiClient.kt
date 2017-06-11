package com.github.reyurnible.news.repository

import com.github.reyurnible.news.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * News Api Client
 */
object NewsApiClient {
    const val API_BASE_URL = "https://newsapi.org"
    const val API_KEY = BuildConfig.API_KEY

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(createClient())
            .addConverterFactory(createConverter())// Gsonで処理するための設定
            .addCallAdapterFactory(createCallAdapter())
            .build()

    val newsApi: NewsApi = retrofit.create(NewsApi::class.java)

    private fun createClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()

    private fun createConverter(): Converter.Factory =
            GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create())

    private fun createCallAdapter(): CallAdapter.Factory =
            RxJava2CallAdapterFactory.create()

}
