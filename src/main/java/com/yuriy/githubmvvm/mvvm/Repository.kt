package com.yuriy.githubmvvm.mvvm

import androidx.lifecycle.LiveData
import com.yuriy.githubmvvm.data.entities.GitHubRepo
import com.yuriy.githubmvvm.data.entities.UserInfo
import com.yuriy.githubmvvm.network.NetDataSource

class Repository {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Repository().also { instance = it }
            }
    }

    val networkSource = NetDataSource()

    fun getReposList(): LiveData<List<GitHubRepo>> {
        return networkSource.getReposList()
    }

    fun getUserInfo(): LiveData<UserInfo> {
        return networkSource.getUserInfo()
    }

    fun updateData(user: String) {
        networkSource.updateData(user)
    }
}