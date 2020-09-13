package com.dp.baseui

import android.content.Context
import android.content.SharedPreferences


class SharedPreferencesProvider(context: Context) {

    private var pref: SharedPreferences

    companion object {
        private const val PREF_NAME = "app_challenge_pref"
    }

    init {
        pref = context.getSharedPreferences(PREF_NAME, 0)
    }

    fun save(key: String, value: String) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun get(key: String): String {
        return get(key, "")
    }


    fun get(key: String, default: String): String {
        return pref.getString(key, default)!!
    }
}
