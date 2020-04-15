package com.yuriy.githubmvvm.network

import android.content.Context
import android.widget.Toast
import com.yuriy.githubmvvm.application.AppClass
import com.yuriy.githubmvvm.data.entities.GitHubRepo
import com.yuriy.githubmvvm.data.entities.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class NetDataSource @Inject constructor(val api: GitHubApi, val context: Context) {

    suspend fun getReposList(user: String): List<GitHubRepo>? {
        return try {
            api.githubService.getReposList(user)
        } catch (exception: IOException) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    exception.message,
                    Toast.LENGTH_LONG
                ).show()
            }
            null
        }
    }

    suspend fun getUserInfo(user: String): UserInfo? {
        try {
            return api.githubService.getUserInfo(user)
        } catch (exception: IOException) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    exception.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return null
    }
}