package com.yuriy.githubmvvm.mvvm

import androidx.lifecycle.ViewModel

class GitHubViewModel(private val repository: Repository) : ViewModel()  {

    fun getReposList() = repository.getReposList()

    fun getUserInfo() = repository.getUserInfo()

    fun updateData(user: String) = repository.updateData(user)

}