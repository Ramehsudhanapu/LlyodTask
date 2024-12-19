# Lloyds Clean Architecture Android Project

This project is a modern Android application built using a Clean Architecture approach, Jetpack Compose for the UI, and other cutting-edge Android development best practices. It demonstrates how to structure an Android app for maintainability, testability, scalability, and robustness. The application is designed to fetch and display product data, showcasing a well-organized and efficient codebase.

## Features

*   **Clean Architecture:** The project strictly adheres to the principles of Clean Architecture, ensuring a clear separation of concerns into distinct layers:
    *   **Presentation Layer:** Built entirely with Jetpack Compose, this layer handles UI rendering and user interactions.
    *   **Domain Layer:** Contains use cases (e.g., `GetCategoryByIDUseCase`) that encapsulate the application's business logic.
    *   **Data Layer:** Manages data fetching and persistence through repositories (e.g., `ProductRepository`) and data sources (e.g., network, database).
*   **Jetpack Compose:** The UI is built using Jetpack Compose, providing a declarative and efficient way to create user interfaces.
*   **Dependency Injection:** Hilt is used for dependency injection, simplifying dependency management and promoting testability.
*   **Networking:** Retrofit is employed for making network requests to fetch data from APIs, with Gson likely used for JSON conversion.
*   **Asynchronous Data Handling:** Kotlin Flows are used to handle asynchronous data streams, making it easy to work with network requests and other asynchronous operations.
*   **Unit Testing:** The project includes comprehensive unit tests using JUnit, Mockito, and `kotlinx-coroutines-test` to ensure the correctness of the business logic and data layers.
*   **Image Loading:** Coil is used for efficient and modern image loading.
*   **Navigation:** Navigation Compose is used for navigation.
*   **Lifecycle:** Lifecycle-runtime-ktx is used for lifecycle.
*   **Core:** Core-ktx is used for core.
*   **Activity:** Activity-compose is used for activity.
*   **Empty State Handling:** The app gracefully handles cases where no data is available, as demonstrated by the `EmptyProduct` composable.
*   **Description Page:** The application features a detailed description page for each product. This page provides comprehensive information about the product, enhancing the user's understanding and experience.
*   **Pager:** The description page utilizes a pager component to allow users to easily swipe between different sections of the product description or related content. This provides a smooth and intuitive way to navigate through detailed information.
* **Dynamic Categories:** The application fetches product categories from an external API and dynamically populates the bottom navigation bar with these categories. This allows for a flexible and up-to-date navigation experience.

## Project Structure

The project is organized into multiple modules, following a Clean Architecture approach:

*   **`app`:** The main application module, responsible for assembling the application and launching the UI.
*   **`features`:** Contains feature modules, each encapsulating a specific part of the application's functionality.
    *   **`assessment`:** (Example) A feature module that likely handles product-related functionality, such as displaying product lists and details.
*   **`core`:** Contains core components and utilities that are shared across modules, such as common UI elements, extensions, and helper functions.
*   **`data`:** Contains data layer, responsible for data fetching and persistence.
*   **`domain`:** Contains domain layer, responsible for business logic.

## Dependencies

The project uses the following key dependencies, which are managed directly in the module-level `build.gradle.kts` files:

*   **Jetpack Compose:**
    *   `androidx.compose.ui:ui`
    *   `androidx.compose.material3:material3`
    *   `androidx.compose.ui:ui-tooling-preview`
*   **Dependency Injection:**
    *   `com.google.dagger:hilt-android`
    *   `androidx.hilt:hilt-navigation-compose`
*   **Networking:**
    *   `com.squareup.retrofit2:retrofit`
    *   `com.squareup.retrofit2:converter-gson`
*   **Asynchronous Data:**
    *   `kotlinx-coroutines-android`
*   **Testing:**
    *   `junit:junit`
    *   `org.mockito:mockito-core`
    *   `org.mockito.kotlin:mockito-kotlin`
    *   `org.jetbrains.kotlinx:kotlinx-coroutines-test`
*   **Image loading:**
    *   `io.coil-kt:coil-compose`
*   **Navigation:**
    *   `androidx.navigation:navigation-compose`
*   **Lifecycle:**
    *   `androidx.lifecycle:lifecycle-runtime-ktx`
*   **Core:**
    *   `androidx.core:core-ktx`
*   **Activity:**
    *   `androidx.activity:activity-compose`
*   **Pager:**
    * `com.google.accompanist:accompanist-pager:0.33.2-alpha`
    * `com.google.accompanist:accompanist-pager-indicators:0.33.2-alpha`

## Getting Started

1.  **Clone the repository:**