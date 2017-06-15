package com.github.reyurnible.news.component.scene.top

import android.content.Context
import android.content.Intent

/**
 * TopScene Extensions
 */
fun TopActivity.Companion.createIntent(context: Context): Intent =
        Intent(context, TopActivity::class.java)