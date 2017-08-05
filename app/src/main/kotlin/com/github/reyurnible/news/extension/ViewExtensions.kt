package com.github.reyurnible.news.extension

import android.support.annotation.IntDef
import android.view.View

/**
 * View Extensions
 */
fun Any?.toVisible(invisible: Int = View.INVISIBLE): Int =
        (this?.let { true } ?: false).toVisible(invisible)

fun Boolean.toVisible(invisible: Int = View.INVISIBLE): Int =
        if (this) View.VISIBLE
        else invisible