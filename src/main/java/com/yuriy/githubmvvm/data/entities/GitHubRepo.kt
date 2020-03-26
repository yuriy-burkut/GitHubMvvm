package com.yuriy.githubmvvm.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "repositories")
@JsonClass(generateAdapter = true)
data class GitHubRepo (
    @PrimaryKey(autoGenerate = false) var id: Long = 0,
    var name: String? = "",
    var private: Boolean = false,
    @Json(name = "open_issues") var openIssues: Int = 0,
    @Json(name = "default_branch") var defaultBranch: String = "",
    @Embedded(prefix = "owner_") var owner: Owner = Owner()
)