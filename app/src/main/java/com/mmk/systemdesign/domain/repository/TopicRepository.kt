package com.mmk.systemdesign.domain.repository

import com.mmk.systemdesign.domain.model.TopicContent
import kotlinx.coroutines.flow.Flow

interface TopicRepository : SystemDesignRepository {
    fun getTopicContent(path: String): Flow<TopicContent>
}
