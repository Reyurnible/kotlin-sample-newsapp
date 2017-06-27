package com.github.reyurnible.news.extension

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Wrapping Rx BehaviorSubject defaultValue object.
 */
class Variable<T> private constructor(defaultValue: T?) {
    companion object {
        fun <T> create(): Variable<T> = Variable(null)
        fun <T> createDefault(value: T): Variable<T> = Variable(value)
    }

    // Call onNext is toSerialized()
    val isAsyncEmit: Boolean = true
    // Value holder
    private val subject: BehaviorSubject<T> =
            defaultValue?.let {
                BehaviorSubject.createDefault(it)
            } ?: BehaviorSubject.create()

    var value: T?
        set(value) {
            value?.let {
                if (isAsyncEmit) subject.toSerialized().onNext(it)
                else subject.onNext(it)
            } ?: throw IllegalArgumentException("Value is not required null")
        }
        get() = if (subject.hasValue()) {
            subject.value
        } else {
            null
        }

    fun value(defaultItem: T): T = subject.blockingFirst(defaultItem)

    fun asObservable(): Observable<T> = subject.hide()
}
