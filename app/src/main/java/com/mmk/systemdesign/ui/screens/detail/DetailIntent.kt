package com.mmk.systemdesign.ui.screens.detail

sealed class DetailIntent {
    data class LoadDetail(val url: String) : DetailIntent()
    data class RefreshDetail(val url: String) : DetailIntent()
}
