package com.github.reyurnible.news.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Activity Extension
 */
fun AppCompatActivity.setContentFragment(@IdRes containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(containerId, fragment).commit()
}
