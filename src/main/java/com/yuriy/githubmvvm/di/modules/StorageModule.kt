package com.yuriy.githubmvvm.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.yuriy.githubmvvm.cache.SpHelper
import com.yuriy.githubmvvm.data.Dao.GitHubDatabase
import com.yuriy.githubmvvm.data.Dao.GitHubUsersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): GitHubDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            GitHubDatabase::class.java, "github.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: GitHubDatabase): GitHubUsersDao {
        return database.gutHubUsersDao()
    }

    @Singleton
    @Provides
    fun provideSpHelper(prefs: SharedPreferences, context: Context): SpHelper {
        return SpHelper(prefs, context)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
    }

}