package com.mmk.systemdesign.ui.screens.home

sealed class HomeIntent {
    object LoadTopics : HomeIntent()
    object RefreshTopics : HomeIntent()
}
