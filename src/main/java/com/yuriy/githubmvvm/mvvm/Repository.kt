package com.yuriy.githubmvvm.mvvm

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.yuriy.githubmvvm.application.AppClass
import com.yuriy.githubmvvm.cache.SpHelper
import com.yuriy.githubmvvm.data.Dao.GitHubDatabase
import com.yuriy.githubmvvm.data.entities.GitHubRepo
import com.yuriy.githubmvvm.data.entities.UserInfo
import com.yuriy.githubmvvm.network.NetDataSource
import kotlinx.coroutines.*

class Repository() {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Repository().also { instance = it }
            }
    }

    private val ioScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val netSource: NetDataSource = NetDataSource()
    private val database: GitHubDatabase = AppClass.getDatabase()
    private val dao = database.gutHubUsersDao()
    private val spCache = SpHelper(AppClass.appContext())

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
        if (AppClass.isOnline()) {
            val repos = netSource.getReposList(user)

            if (repos != null) {
                reposList.postValue(repos)
                dao.insertRepos(repos)
            }
        } else {
            dao.getReposForUser(user).map { reposList }
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    AppClass.appContext(),
                    "You don't have an internet connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun fetchUserInfo(user: String) = ioScope.launch {
        if (AppClass.isOnline()) {
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
                    AppClass.appContext(),
                    "You don't have an internet connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}