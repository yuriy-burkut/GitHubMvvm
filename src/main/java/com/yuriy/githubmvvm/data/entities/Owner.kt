package com.yuriy.githubmvvm.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Owner(
    val id: Long = 0,
    val login: String = ""
)