package com.mmk.systemdesign.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mmk.systemdesign.SystemDesignApp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mmk.systemdesign.domain.model.Topic
import com.mmk.systemdesign.ui.screens.detail.DetailScreen
import com.mmk.systemdesign.ui.screens.detail.DetailViewModel
import com.mmk.systemdesign.ui.screens.home.HomeScreen
import com.mmk.systemdesign.ui.screens.home.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val appContainer = (context.applicationContext as SystemDesignApp).appContainer
    val scope = rememberCoroutineScope()

    val homeViewModel: HomeViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(appContainer.getTopicsUseCase) as T
            }
        }
    )

    val detailViewModel: DetailViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return DetailViewModel(appContainer.getTopicDetailUseCase) as T
            }
        }
    )

    val navigator = rememberListDetailPaneScaffoldNavigator<Topic>()

    BackHandler(navigator.canNavigateBack()) {
        scope.launch {
            navigator.navigateBack()
        }
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            HomeScreen(
                viewModel = homeViewModel,
                onTopicClick = { topic ->
                    scope.launch {
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, topic)
                    }
                }
            )
        },
        detailPane = {
            val topic = navigator.currentDestination?.contentKey
            DetailScreen(
                viewModel = detailViewModel,
                topic = topic,
                onBack = {
                    scope.launch {
                        navigator.navigateBack()
                    }
                }
            )
        }
    )
}
