package com.mmk.systemdesign.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmk.systemdesign.domain.usecase.GetTopicDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getTopicDetailUseCase: GetTopicDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.LoadDetail -> loadDetail(intent.url)
            is DetailIntent.RefreshDetail -> loadDetail(intent.url)
        }
    }

    private fun loadDetail(url: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            getTopicDetailUseCase(url).collectLatest { result ->
                result.onSuccess { content ->
                    _state.update { it.copy(isLoading = false, content = content) }
                }.onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                }
            }
        }
    }
}
