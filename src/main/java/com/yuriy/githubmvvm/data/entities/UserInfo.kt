package com.yuriy.githubmvvm.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "users")
@JsonClass(generateAdapter = true)
data class UserInfo (
    @PrimaryKey(autoGenerate = false) var id: Long = 0,
    var login: String = "",
    @Json(name = "avatar_url") var avatarUrl : String = "",
    var type: String = "",
    var email: String? = null,
    @Json(name = "public_repos") var publicRepos : Int = 0,
    var followers: Int = 0,
    var following: Int = 0,
    @Json(name = "created_at") var createdAt : String = "",
    @Json(name = "updated_at") var updatedAt : String = ""
)


