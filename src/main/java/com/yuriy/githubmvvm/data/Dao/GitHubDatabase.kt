package com.yuriy.githubmvvm.data.Dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yuriy.githubmvvm.data.entities.GitHubRepo
import com.yuriy.githubmvvm.data.entities.UserInfo

@Database(entities = [UserInfo::class, GitHubRepo::class], version = 1, exportSchema = false)
abstract class GitHubDatabase : RoomDatabase() {

    abstract fun gutHubUsersDao(): GitHubUsersDao

/*    companion object {
        @Volatile
        private var instance: GitHubDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GitHubDatabase::class.java, "github.db"
            )
                .build()
    }*/
}