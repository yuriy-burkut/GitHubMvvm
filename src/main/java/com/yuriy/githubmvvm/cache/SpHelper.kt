package com.yuriy.githubmvvm.cache

import android.content.Context
import com.yuriy.githubmvvm.R

class SpHelper(private val context: Context) {

    private val prefs = context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)

    var lastLogin: String?
        get() = prefs.getString(context.getString(R.string.login_key), "")
        set(value) {
            prefs.edit().putString(context.getString(R.string.login_key), value).apply()
        }
}