package com.mmk.systemdesign.data.mapper

import com.mmk.systemdesign.data.model.GithubContentDto
import com.mmk.systemdesign.domain.model.Topic
import com.mmk.systemdesign.domain.model.TopicLevel
import java.util.Locale

fun GithubContentDto.toTopic(): Topic {
    val level = when (this.name) {
        "authentication-and-authorization",
        "back-of-the-envelop-estimation",
        "database",
        "database-indexing",
        "database-transaction",
        "domain-name-system",
        "http-tls-and-https",
        "proxy",
        "rest-api",
        "scalability",
        "stateless-stateful-architecture" -> TopicLevel.BEGINNER

        "caching",
        "cap-theorem",
        "cdn",
        "concurrency-and-parallelism",
        "database-replication",
        "database-sharding",
        "message-queue",
        "performance-metrics",
        "polling-web-socket-server-sent-events-webhooks",
        "rate-limiter" -> TopicLevel.INTERMEDIATE

        "bloom-filter",
        "elasticsearch",
        "real-world-problems",
        "reliability" -> TopicLevel.ADVANCED

        else -> TopicLevel.BEGINNER
    }

    // Convert slug name to a more readable name
    val formattedName = this.name.split("-")
        .joinToString(" ") { it.replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString() } }

    return Topic(
        id = this.sha,
        title = formattedName,
        category = level.name,
        url = this.path
    )
}
