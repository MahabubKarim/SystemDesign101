package com.mmk.systemdesign.data.repository

import com.mmk.systemdesign.data.source.remote.SystemDesignRemoteDataSource
import com.mmk.systemdesign.domain.model.TopicCategory
import com.mmk.systemdesign.domain.repository.SystemDesignRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SystemDesignRepositoryImpl(
    private val remoteDataSource: SystemDesignRemoteDataSource
) : SystemDesignRepository {

    override fun getTopics(): Flow<Result<List<TopicCategory>>> = flow {
        emit(remoteDataSource.fetchTopics())
    }

    override fun getTopicDetail(url: String): Flow<Result<String>> = flow {
        emit(remoteDataSource.fetchTopicDetail(url))
    }
}
