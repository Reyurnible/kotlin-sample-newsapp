package com.github.reyurnible.news.repository

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
    // TODO Please Replace Your API KEY
    const val API_KEY = "a3e6ac56bd5b4678a81cdd139802b6b4"

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
