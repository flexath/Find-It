package com.flexath.core.utils

import android.content.Context
import android.content.Intent

object ContextUtils {
    fun Context.shareLink(url: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,url)

        val shareIntent = Intent.createChooser(intent,null)
        startActivity(shareIntent)
    }
}