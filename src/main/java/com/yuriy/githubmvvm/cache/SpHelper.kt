package com.yuriy.githubmvvm.cache

import android.content.Context
import android.content.SharedPreferences
import com.yuriy.githubmvvm.R

class SpHelper(private val prefs: SharedPreferences, val context: Context) {

    var lastLogin: String?
        get() = prefs.getString(context.getString(R.string.login_key), "")
        set(value) {
            prefs.edit().putString(context.getString(R.string.login_key), value).apply()
        }
}