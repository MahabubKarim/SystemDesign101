package com.mmk.systemdesign.ui.screens.detail

import androidx.compose.runtime.Immutable

@Immutable
data class DetailState(
    val isLoading: Boolean = false,
    val content: String? = null,
    val error: String? = null
)
