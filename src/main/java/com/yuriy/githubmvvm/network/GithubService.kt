package com.yuriy.githubmvvm.network

import com.yuriy.githubmvvm.data.entities.GitHubRepo
import com.yuriy.githubmvvm.data.entities.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {

    @GET("users/{user}/repos")
    suspend fun getReposList(@Path("user") user : String) : List<GitHubRepo>

    @GET("users/{user}")
    suspend fun getUserInfo(@Path("user") user: String) : UserInfo

}