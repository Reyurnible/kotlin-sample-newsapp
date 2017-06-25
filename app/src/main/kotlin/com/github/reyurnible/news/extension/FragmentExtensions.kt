package com.github.reyurnible.news.extension

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Extensions for Fragment
 */
inline fun <F : Fragment> F.applyArguments(initialize: Bundle.() -> Unit): F {
    arguments = Bundle().apply { initialize() }
    return this
}
