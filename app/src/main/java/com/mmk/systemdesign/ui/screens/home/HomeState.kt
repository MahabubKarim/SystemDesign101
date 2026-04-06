package com.mmk.systemdesign.ui.screens.home

import androidx.compose.runtime.Immutable
import com.mmk.systemdesign.domain.model.TopicCategory

/**
In Jetpack Compose, you should generally not annotate your ViewModel with @Stable or @Immutable.
Instead, you should apply @Immutable to the UI State classes that the ViewModel exposes.

Why?

• @Immutable is a strict promise that properties will never change. Since a ViewModel manages
changing state (e.g., via MutableStateFlow), marking it @Immutable is semantically incorrect and
can lead to UI update bugs.

• @Stable is more appropriate than @Immutable for objects that change but notify the Compose
runtime. However, modern Compose (especially with Strong Skipping Mode) can infer stability
for most ViewModels automatically if they are only passed to top-level "Screen" Composables.

• Performance is mostly gained from the UI State. When your state class (like HomeState) is
marked @Immutable, Compose can skip recomposing any UI component that takes it as a parameter
if the data hasn't changed.

Changes Made:
1. Removed @Immutable and @Stable from HomeViewModel and DetailViewModel.
2. Added @Immutable to HomeState and DetailState to ensure efficient recomposition when the state is unchanged.*/

@Immutable
data class HomeState(
    val isLoading: Boolean = false,
    val categories: List<TopicCategory> = emptyList(),
    val error: String? = null
)
