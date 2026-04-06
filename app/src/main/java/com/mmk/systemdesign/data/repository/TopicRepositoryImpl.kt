package com.mmk.systemdesign.data.repository

import android.util.Base64
import com.mmk.systemdesign.data.mapper.toTopic
import com.mmk.systemdesign.data.remote.GithubApiService
import com.mmk.systemdesign.domain.model.TopicCategory
import com.mmk.systemdesign.domain.model.TopicContent
import com.mmk.systemdesign.domain.repository.TopicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.nio.charset.StandardCharsets

class TopicRepositoryImpl(
    private val apiService: GithubApiService
) : TopicRepository {
    override fun getTopics(): Flow<Result<List<TopicCategory>>> = flow {
        try {
            val dtos = apiService.getSections()
            val topics = dtos.map { it.toTopic() }
            val categories = topics.groupBy { it.category }
                .map { (category, topics) -> TopicCategory(category, topics) }
            emit(Result.success(categories))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getTopicDetail(url: String): Flow<Result<String>> = flow {
        try {
            val dto = apiService.getFileContent(url)
            val decodedContent = if (dto.content != null) {
                val cleanedContent = dto.content
                    .replace("\n", "")
                    .replace("\r", "")
                val decodedBytes = Base64.decode(cleanedContent, Base64.DEFAULT)
                String(decodedBytes, StandardCharsets.UTF_8)
            } else ""
            emit(Result.success(decodedContent))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getTopicContent(path: String): Flow<TopicContent> = flow {
        val dto = apiService.getFileContent(path)
        val decodedContent = if (dto.content != null) {
            val cleanedContent = dto.content
                .replace("\n", "")
                .replace("\r", "")
            val decodedBytes = Base64.decode(cleanedContent, Base64.DEFAULT)
            String(decodedBytes, StandardCharsets.UTF_8)
        } else ""
        emit(TopicContent(topicId = dto.sha, markdown = decodedContent))
    }
}
