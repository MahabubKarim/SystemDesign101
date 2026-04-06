package com.mmk.systemdesign.domain.repository

import com.mmk.systemdesign.domain.model.Topic
import com.mmk.systemdesign.domain.model.TopicCategory
import kotlinx.coroutines.flow.Flow

interface SystemDesignRepository {
    fun getTopics(): Flow<Result<List<TopicCategory>>>
    fun getTopicDetail(url: String): Flow<Result<String>>
}
