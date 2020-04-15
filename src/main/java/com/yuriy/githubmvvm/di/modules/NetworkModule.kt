package com.yuriy.githubmvvm.di.modules

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yuriy.githubmvvm.network.GitHubApi
import com.yuriy.githubmvvm.network.NetHelper
import com.yuriy.githubmvvm.network.interceptors.ConnectivityInterceptor
import com.yuriy.githubmvvm.network.interceptors.GitHubErrorsInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): GitHubApi {
        return GitHubApi(retrofit)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(ConnectivityInterceptor(context))
            .addInterceptor(GitHubErrorsInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetHelper(context: Context): NetHelper {
        return NetHelper(context)
    }
}