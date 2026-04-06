package com.mmk.systemdesign.domain.usecase

import com.mmk.systemdesign.domain.model.TopicCategory
import com.mmk.systemdesign.domain.repository.SystemDesignRepository
import kotlinx.coroutines.flow.Flow

class GetTopicsUseCase(private val repository: SystemDesignRepository) {
    operator fun invoke(): Flow<Result<List<TopicCategory>>> = repository.getTopics()
}
