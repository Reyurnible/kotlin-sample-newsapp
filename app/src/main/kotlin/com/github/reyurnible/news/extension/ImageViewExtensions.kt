package com.github.reyurnible.news.extension

import android.net.Uri
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.io.File

/**
 * ImageView Extensions
 */
private val DEFAULT_PLACEHOLDER = null
private val DEFAULT_ERROR = null
private val DEFAULT_PRIORITY = Picasso.Priority.NORMAL

fun ImageView.load(url: Uri?,
                   @DrawableRes placeHolder: Int? = DEFAULT_PLACEHOLDER,
                   @DrawableRes error: Int? = DEFAULT_ERROR,
                   priority: Picasso.Priority = DEFAULT_PRIORITY,
                   onSuccessScaleType: ImageView.ScaleType? = null
) {
    url ?: return
    Picasso.with(this.context).load(url).loadSettings(this, placeHolder, error, priority, onSuccessScaleType)
}

fun ImageView.load(path: String?,
                   @DrawableRes placeHolder: Int? = DEFAULT_PLACEHOLDER,
                   @DrawableRes error: Int? = DEFAULT_ERROR,
                   priority: Picasso.Priority = DEFAULT_PRIORITY,
                   onSuccessScaleType: ImageView.ScaleType? = null
) {
    if (path.isNullOrBlank()) {
        return
    }
    Picasso.with(this.context).load(path).loadSettings(this, placeHolder, error, priority, onSuccessScaleType)
}

fun ImageView.load(file: File?,
                   @DrawableRes placeHolder: Int? = DEFAULT_PLACEHOLDER,
                   @DrawableRes error: Int? = DEFAULT_ERROR,
                   priority: Picasso.Priority = DEFAULT_PRIORITY,
                   onSuccessScaleType: ImageView.ScaleType? = null
) {
    file ?: return
    Picasso.with(this.context).load(file).loadSettings(this, placeHolder, error, priority, onSuccessScaleType)
}

fun ImageView.load(@DrawableRes resourceId: Int?,
                   @DrawableRes placeHolder: Int? = DEFAULT_PLACEHOLDER,
                   @DrawableRes error: Int? = DEFAULT_ERROR,
                   priority: Picasso.Priority = DEFAULT_PRIORITY,
                   onSuccessScaleType: ImageView.ScaleType? = null
) {
    resourceId ?: return
    Picasso.with(this.context).load(resourceId).loadSettings(this, placeHolder, error, priority, onSuccessScaleType)
}

private fun RequestCreator.loadSettings(
        imageView: ImageView,
        @DrawableRes placeHolder: Int?,
        @DrawableRes error: Int?,
        priority: Picasso.Priority,
        onSuccessScaleType: ImageView.ScaleType? = null
) {
    this.apply {
        placeHolder?.let { this.placeholder(placeHolder) }
        error?.let { this.error(error) }
        priority(priority)
        fit()
    }.into(imageView, object : Callback {
        override fun onSuccess() {
            onSuccessScaleType?.let {
                imageView.scaleType = it
            }
        }

        override fun onError() {

        }
    })
}