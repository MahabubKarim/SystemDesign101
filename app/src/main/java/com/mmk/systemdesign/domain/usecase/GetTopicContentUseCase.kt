package com.mmk.systemdesign.domain.usecase

import com.mmk.systemdesign.domain.model.TopicContent
import com.mmk.systemdesign.domain.repository.TopicRepository
import kotlinx.coroutines.flow.Flow

class GetTopicContentUseCase(private val repository: TopicRepository) {
    operator fun invoke(path: String): Flow<TopicContent> = repository.getTopicContent(path)
}
