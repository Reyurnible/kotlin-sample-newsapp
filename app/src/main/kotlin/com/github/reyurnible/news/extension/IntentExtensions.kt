package com.github.reyurnible.news.extension

import android.content.Context
import android.content.Intent

/**
 * Intent Extensions
 */
fun Intent.start(context: Context) {
    context.startActivity(this)
}
