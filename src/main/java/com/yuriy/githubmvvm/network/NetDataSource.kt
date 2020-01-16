package com.yuriy.githubmvvm.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuriy.githubmvvm.data.entities.GitHubRepo
import com.yuriy.githubmvvm.data.entities.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class NetDataSource {

    val api = GitHubApi().createService()

    private val reposData = MutableLiveData<List<GitHubRepo>>()
    private val userData = MutableLiveData<UserInfo>()

    fun getReposList() : LiveData<List<GitHubRepo>> {

        return reposData
    }

    fun getUserInfo() : LiveData<UserInfo> {

        return userData
    }

    fun updateData(user: String) {
        fetchReposList(user)
        fetchUserInfo(user)
    }

    private fun fetchReposList(user: String){

        api.getReposList(user).enqueue(object : Callback<List<GitHubRepo>> {

            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {
                throw IOException(t)
            }

            override fun onResponse(
                call: Call<List<GitHubRepo>>,
                response: Response<List<GitHubRepo>>
            ) {
                if (response.isSuccessful) {
                    reposData.value = response.body()
                }
            }
        })
    }

    private fun fetchUserInfo(user: String) {

        api.getUserInfo(user).enqueue(object : Callback<UserInfo> {
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                throw IOException(t)
            }

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                if (response.isSuccessful) {
                    userData.value = response.body()
                }
            }
        })

    }

}