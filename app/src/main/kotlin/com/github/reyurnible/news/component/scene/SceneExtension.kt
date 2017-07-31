package com.github.reyurnible.news.component.scene

import android.content.DialogInterface
import android.support.v4.app.Fragment
import com.github.reyurnible.news.R
import com.github.reyurnible.news.repository.DomainError
import org.jetbrains.anko.support.v4.alert

/**
 * Scene (Fragment or Activity) Extension
 */
fun Fragment.alertError(error: DomainError): DialogInterface =
        when (error) {
            is DomainError.Api -> alertApiError(error)
            is DomainError.Network -> alertNetworkError(error)
            is DomainError.Unknown -> alertUnknownError(error)
        }

private fun Fragment.alertApiError(error: DomainError.Api): DialogInterface =
        alert(title = R.string.error_api_title, message = R.string.error_api_message).build()

private fun Fragment.alertNetworkError(error: DomainError.Network): DialogInterface =
        alert(title = R.string.error_network_title, message = R.string.error_network_message).build()

private fun Fragment.alertUnknownError(error: DomainError.Unknown): DialogInterface =
        alert(title = R.string.error_unknown_title, message = R.string.error_unknown_message).build()
