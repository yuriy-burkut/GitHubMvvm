package com.yuriy.githubmvvm.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yuriy.githubmvvm.data.entities.GitHubRepo
import com.yuriy.githubmvvm.data.entities.UserInfo
import javax.inject.Inject

class GitHubViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val lastLogin: String?
        get() = repository.lastLogin

    fun findUser(login: String) {
        repository.updateData(login)
    }

    fun getReposList(): LiveData<List<GitHubRepo>> = repository.reposList

    fun getUserInfo(): LiveData<UserInfo> = repository.userData
}