package com.mmk.systemdesign.di

import com.mmk.systemdesign.data.remote.GithubApiService
import com.mmk.systemdesign.data.repository.TopicRepositoryImpl
import com.mmk.systemdesign.domain.repository.TopicRepository
import com.mmk.systemdesign.domain.usecase.GetTopicContentUseCase
import com.mmk.systemdesign.domain.usecase.GetTopicsUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceLocator {
    private const val BASE_URL = "https://api.github.com/"

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    val apiService: GithubApiService by lazy {
        retrofit.create(GithubApiService::class.java)
    }

    val repository: TopicRepository by lazy {
        TopicRepositoryImpl(apiService)
    }

    val getTopicsUseCase: GetTopicsUseCase by lazy {
        GetTopicsUseCase(repository)
    }

    val getTopicContentUseCase: GetTopicContentUseCase by lazy {
        GetTopicContentUseCase(repository)
    }
}
