package com.yuriy.githubmvvm.mvvm

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.yuriy.githubmvvm.application.AppClass
import com.yuriy.githubmvvm.cache.SpHelper
import com.yuriy.githubmvvm.data.Dao.GitHubUsersDao
import com.yuriy.githubmvvm.data.entities.GitHubRepo
import com.yuriy.githubmvvm.data.entities.UserInfo
import com.yuriy.githubmvvm.network.NetDataSource
import com.yuriy.githubmvvm.network.NetHelper
import kotlinx.coroutines.*
import javax.inject.Inject

class Repository @Inject constructor(
    private val netSource: NetDataSource,
    val dao: GitHubUsersDao,
    private val spCache: SpHelper,
    private val netHelper: NetHelper,
    val context: Context
) {

    private val ioScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    val userData = MutableLiveData<UserInfo>()
    val reposList = MutableLiveData<List<GitHubRepo>>()

    var lastLogin: String?
        get() = spCache.lastLogin
        set(value) {
            spCache.lastLogin = value
        }

    fun updateData(login: String) {
        fetchReposList(login)
        fetchUserInfo(login)
    }

    private fun fetchReposList(user: String) = ioScope.launch {
        if (netHelper.isOnline()) {
            val repos = netSource.getReposList(user)

            if (repos != null) {
                reposList.postValue(repos)
                dao.insertRepos(repos)
            }
        } else {
            dao.getReposForUser(user).map { reposList }
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "You don't have an internet connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun fetchUserInfo(user: String) = ioScope.launch {
        if (netHelper.isOnline()) {
            val userInfo = netSource.getUserInfo(user)
            if (userInfo != null) {
                userData.postValue(userInfo)
                dao.insertUser(userInfo)
                lastLogin = user
            }
        } else {
            dao.getUserInfo(user).map { userData }
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "You don't have an internet connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}