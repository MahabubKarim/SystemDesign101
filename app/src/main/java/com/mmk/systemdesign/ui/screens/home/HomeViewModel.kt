package com.mmk.systemdesign.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmk.systemdesign.domain.usecase.GetTopicsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTopicsUseCase: GetTopicsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        handleIntent(HomeIntent.LoadTopics)
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.LoadTopics -> loadTopics()
            HomeIntent.RefreshTopics -> loadTopics()
        }
    }

    private fun loadTopics() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            getTopicsUseCase().collectLatest { result ->
                result.onSuccess { categories ->
                    _state.update { it.copy(isLoading = false, categories = categories) }
                }.onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                }
            }
        }
    }
}
