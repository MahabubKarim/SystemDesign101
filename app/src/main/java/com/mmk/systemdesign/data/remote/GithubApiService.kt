package com.mmk.systemdesign.data.remote

import com.mmk.systemdesign.data.model.GithubContentDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("repos/lahin31/system-design-bangla/contents/sections")
    suspend fun getSections(): List<GithubContentDto>

    @GET("repos/lahin31/system-design-bangla/contents/{path}/README.md")
    suspend fun getFileContent(@Path("path", encoded = true) path: String): GithubContentDto
}
