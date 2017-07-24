package com.github.reyurnible.news.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.github.reyurnible.news.R

/**
 * Activity Extension
 */
fun AppCompatActivity.setContentFragment(@IdRes containerId: Int = R.id.layout_container, fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(containerId, fragment).commit()
}
