package com.mmk.systemdesign.domain.usecase

import com.mmk.systemdesign.domain.repository.SystemDesignRepository
import kotlinx.coroutines.flow.Flow

class GetTopicDetailUseCase(private val repository: SystemDesignRepository) {
    operator fun invoke(url: String): Flow<Result<String>> = repository.getTopicDetail(url)
}
