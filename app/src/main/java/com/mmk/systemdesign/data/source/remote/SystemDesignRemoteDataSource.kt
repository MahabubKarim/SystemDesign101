package com.mmk.systemdesign.data.source.remote

import com.mmk.systemdesign.domain.model.Topic
import com.mmk.systemdesign.domain.model.TopicCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class SystemDesignRemoteDataSource {
    private val baseUrl = "https://lahin31.github.io/system-design-bangla/"

    suspend fun fetchTopics(): Result<List<TopicCategory>> = withContext(Dispatchers.IO) {
        try {
            val doc: Document = Jsoup.connect(baseUrl).get()
            val categories = mutableListOf<TopicCategory>()
            
            // The structure of the site seems to have headings as categories and lists as topics
            // This is a common pattern for these kinds of documentation sites
            val content = doc.select("div.markdown-body")
            var currentCategoryName = "General"
            var currentTopics = mutableListOf<Topic>()

            content.first()?.children()?.forEach { element ->
                when {
                    element.tagName() == "h2" || element.tagName() == "h3" -> {
                        if (currentTopics.isNotEmpty()) {
                            categories.add(TopicCategory(currentCategoryName, currentTopics.toList()))
                            currentTopics.clear()
                        }
                        currentCategoryName = element.text()
                    }
                    element.tagName() == "ul" || element.tagName() == "ol" -> {
                        element.select("li").forEach { li ->
                            val link = li.select("a").first()
                            if (link != null) {
                                val title = li.text()
                                val href = link.attr("abs:href")
                                val id = href.substringAfterLast("#", "")
                                currentTopics.add(Topic(id, title, currentCategoryName, href))
                            }
                        }
                    }
                }
            }
            if (currentTopics.isNotEmpty()) {
                categories.add(TopicCategory(currentCategoryName, currentTopics.toList()))
            }
            Result.success(categories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchTopicDetail(url: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val doc: Document = Jsoup.connect(url).get()
            val sectionId = if (url.contains("#")) url.substringAfterLast("#") else ""
            
            val content = if (sectionId.isNotEmpty()) {
                val element = doc.getElementById(sectionId)
                if (element != null) {
                    val sb = StringBuilder()
                    sb.append(element.outerHtml())
                    var next = element.nextElementSibling()
                    while (next != null && !listOf("h1", "h2", "h3", "h4").contains(next.tagName())) {
                        sb.append(next.outerHtml())
                        next = next.nextElementSibling()
                    }
                    sb.toString()
                } else {
                    doc.select("div.markdown-body").html()
                }
            } else {
                doc.select("div.markdown-body").html()
            }
            
            if (content.isBlank()) {
                Result.success(doc.body().html())
            } else {
                Result.success(content)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
