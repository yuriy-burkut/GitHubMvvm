package com.yuriy.githubmvvm.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfo (

    val login: String,
    @Json(name = "avatar_url") val avatarUrl : String,
    val type: String,
    val email: String?,
    @Json(name = "public_repos") val publicRepos : Int,
    val followers: Int,
    val following: Int,
    @Json(name = "created_at") val createdAt : String,
    @Json(name = "updated_at") val updatedAt : String

)


