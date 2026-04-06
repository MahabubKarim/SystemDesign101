package com.mmk.systemdesign.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Topic(
    val id: String,
    val title: String,
    val category: String,
    val url: String,
    val content: String? = null
) : Parcelable


@Parcelize
data class TopicCategory(
    val name: String,
    val topics: List<Topic>
) : Parcelable

