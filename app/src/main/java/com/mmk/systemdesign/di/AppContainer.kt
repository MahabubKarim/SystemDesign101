package com.mmk.systemdesign.di

import com.mmk.systemdesign.data.repository.SystemDesignRepositoryImpl
import com.mmk.systemdesign.data.source.remote.SystemDesignRemoteDataSource
import com.mmk.systemdesign.domain.repository.SystemDesignRepository
import com.mmk.systemdesign.domain.usecase.GetTopicDetailUseCase
import com.mmk.systemdesign.domain.usecase.GetTopicsUseCase

class AppContainer {
    private val remoteDataSource = SystemDesignRemoteDataSource()
    private val repository: SystemDesignRepository = SystemDesignRepositoryImpl(remoteDataSource)
    val getTopicsUseCase = GetTopicsUseCase(repository)
    val getTopicDetailUseCase = GetTopicDetailUseCase(repository)
}
