# System Design 101 (Bangla) 📚

An Android application built with modern development practices to help developers learn System Design in Bangla. The application scrapes and displays content from the [System Design Bangla GitHub Page](https://lahin31.github.io/system-design-bangla/).
*Mobile App Version of [lahin31](https://github.com/lahin31)*

## ✨ Features

- **Topic Explorer**: Browse through various System Design topics categorized by sections (e.g., Scaling, Database Indexing, Caching, etc.).
- **Detailed Reading**: View comprehensive content for each topic with a clean reading experience.
- **Adaptive UI**: Built with Jetpack Compose Adaptive components, offering a responsive List-Detail Pane layout that works seamlessly on phones, foldables, and tablets.
- **State Persistence**: Handles process death and configuration changes gracefully using `Parcelable` navigation state.

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/compose) (Material 3)
- **Architecture**: Clean Architecture with MVVM (Model-View-ViewModel)
- **Asynchronous Programming**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
- **Dependency Management**: [Gradle Version Catalog](https://developer.android.com/build/migrate-to-catalogs) (libs.versions.toml)
- **Navigation**: [Jetpack Compose Adaptive Navigation](https://developer.android.com/develop/ui/compose/layouts/adaptive)
- **Web Scraping**: [Jsoup](https://jsoup.org/)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/) (Infrastructure ready)

## 🏗️ Architecture Overview

The project follows a modular and clean architecture pattern:

- **`domain`**: Contains the business logic, including `Model` definitions and `UseCase`s. Completely independent of Android and UI frameworks.
- **`data`**: Implements the repository pattern, managing data sources (currently web scraping via `Jsoup`).
- **`ui`**: Jetpack Compose-based UI layer.
  - **`screens`**: Contains Screen-level composables and their respective `ViewModel`s and `State` holders.
  - **`theme`**: Material 3 theme configuration.

## 🚀 Getting Started

1. Clone the repository.
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Sync the project with Gradle files.
4. Run the `app` module on an emulator or a physical device.

## 📝 Performance Optimizations

- **Stability**: UI State classes are annotated with `@Immutable` to help the Compose compiler optimize recompositions.
- **State Handling**: ViewModels avoid direct `@Stable` or `@Immutable` annotations, letting Compose's **Strong Skipping Mode** handle it efficiently while keeping state logic robust.

## 🤝 Contribution

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open a Pull Request.

---
