package com.yuriy.githubmvvm.application

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.yuriy.githubmvvm.data.Dao.GitHubDatabase

class AppClass : Application() {

    init {
        instance = this
    }

    companion object {

        private var instance: AppClass? = null
        private lateinit var database: GitHubDatabase

        fun appContext(): Context {
            return instance!!.applicationContext
        }

        fun getDatabase() : GitHubDatabase {
            return database
        }

        fun isOnline() : Boolean {
            val connectivityManager = instance!!.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val networkInfo =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return networkInfo != null && networkInfo.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

        }
    }

    override fun onCreate() {
        super.onCreate()
        database = GitHubDatabase.invoke(this.applicationContext)

    }
}