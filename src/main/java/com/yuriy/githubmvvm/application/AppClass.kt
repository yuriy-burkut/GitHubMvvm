package com.yuriy.githubmvvm.application

import android.app.Application
import com.yuriy.githubmvvm.di.components.AppComponent
import com.yuriy.githubmvvm.di.components.DaggerAppComponent

class AppClass : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}