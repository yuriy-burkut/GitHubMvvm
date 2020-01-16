package com.yuriy.githubmvvm.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubRepo (

    val name: String,
    val private: Boolean,
    @Json(name = "open_issues") val openIssues : Int,
    @Json(name = "default_branch") val defaultBranch : String

)
