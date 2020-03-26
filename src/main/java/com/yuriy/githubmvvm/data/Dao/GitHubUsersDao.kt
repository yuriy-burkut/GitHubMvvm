package com.yuriy.githubmvvm.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuriy.githubmvvm.data.entities.GitHubRepo
import com.yuriy.githubmvvm.data.entities.UserInfo

@Dao
interface GitHubUsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userInfo: UserInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<GitHubRepo>)

    @Query("select * from users where login = (:user)")
    fun getUserInfo(user: String) : LiveData<UserInfo>

    @Query("select * from repositories where owner_login = (:user)")
    fun getReposForUser(user: String) : LiveData<List<GitHubRepo>>

}