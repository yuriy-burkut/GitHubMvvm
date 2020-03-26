package com.yuriy.githubmvvm.network

import android.widget.Toast
import com.yuriy.githubmvvm.application.AppClass
import com.yuriy.githubmvvm.data.entities.GitHubRepo
import com.yuriy.githubmvvm.data.entities.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class NetDataSource {

    private val api = GitHubApi().createService()

    suspend fun getReposList(user: String): List<GitHubRepo>? {
        try {
            return api.getReposList(user)
        } catch (exception: IOException) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    AppClass.appContext(),
                    exception.message,
                    Toast.LENGTH_LONG
                ).show()
            }
            return null
        }
    }

    suspend fun getUserInfo(user: String): UserInfo? {
        try {
            return api.getUserInfo(user)
        } catch (exception: IOException) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    AppClass.appContext(),
                    exception.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return null
    }
}