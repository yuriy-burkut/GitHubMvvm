package com.yuriy.githubmvvm.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yuriy.githubmvvm.application.AppClass
import com.yuriy.githubmvvm.network.interceptors.ConnectivityInterceptor
import com.yuriy.githubmvvm.network.interceptors.GitHubErrorsInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class GitHubApi(retrofit: Retrofit) {

/*    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val connectivityInterceptor = ConnectivityInterceptor(AppClass.appContext())
    private val errorsInterceptor = GitHubErrorsInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .protocols(listOf(Protocol.HTTP_1_1))
        .addInterceptor(connectivityInterceptor)
        .addInterceptor(errorsInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    fun createService() : GithubService {
        return retrofit.create(GithubService::class.java)
    }*/

    val githubService: GithubService = retrofit.create(GithubService::class.java)
}