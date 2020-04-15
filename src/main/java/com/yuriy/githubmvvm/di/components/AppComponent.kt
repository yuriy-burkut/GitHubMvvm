package com.yuriy.githubmvvm.di.components

import android.content.Context
import com.yuriy.githubmvvm.di.modules.AppModule
import com.yuriy.githubmvvm.di.modules.NetworkModule
import com.yuriy.githubmvvm.di.modules.StorageModule
import com.yuriy.githubmvvm.ui.activities.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, StorageModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun inject(activity: MainActivity)

}