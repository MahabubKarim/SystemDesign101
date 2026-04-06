package com.mmk.systemdesign.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubContentDto(
    val name: String,
    val path: String,
    val sha: String,
    val size: Int,
    val url: String,
    @Json(name = "html_url") val htmlUrl: String,
    @Json(name = "git_url") val gitUrl: String,
    @Json(name = "download_url") val downloadUrl: String?,
    val type: String,
    val content: String? = null,
    val encoding: String? = null
)
