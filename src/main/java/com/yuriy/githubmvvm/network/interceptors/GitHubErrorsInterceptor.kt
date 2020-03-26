package com.yuriy.githubmvvm.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class GitHubErrorsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code() != 200) {
            throw IOException(response.message())
        } else return response
    }
}