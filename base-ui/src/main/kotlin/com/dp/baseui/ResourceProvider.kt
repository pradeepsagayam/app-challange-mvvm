package com.dp.baseui

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getString(@StringRes resId: Int, value: String?): String {
        return context.getString(resId, value)
    }

}
