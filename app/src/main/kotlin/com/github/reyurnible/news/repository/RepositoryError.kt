package com.github.reyurnible.news.repository

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import java.net.UnknownHostException

/**
 * Repository Error Types
 */
sealed class RepositoryError : Throwable() {
    // Api Error(TODO add status code error handle)
    data class Api(val statusCode: Int) : RepositoryError()

    // Network Error
    object Network : RepositoryError()

    // Unknown Error
    data class Unknown(val original: Throwable) : RepositoryError()
}

fun parseError(throwable: Throwable): RepositoryError =
        when (throwable) {
            is HttpException -> RepositoryError.Api(throwable.code())
            is UnknownHostException -> RepositoryError.Network
            else -> RepositoryError.Unknown(throwable)
        }

inline fun <reified T> Observable<T>.addDomainErrorHandle(): Observable<T> =
        onErrorResumeNext(Function<Throwable, Observable<T>> { Observable.error<T>(parseError(it)) })

inline fun <reified T> Flowable<T>.addDomainErrorHandle(): Flowable<T> =
        onErrorResumeNext(Function<Throwable, Flowable<T>> { Flowable.error<T>(parseError(it)) })

inline fun <reified T> Single<T>.addDomainErrorHandle(): Single<T> =
        onErrorResumeNext { Single.error<T>(parseError(it)) }

fun Completable.addDomainErrorHandle(): Completable =
        onErrorResumeNext { Completable.error(parseError(it)) }