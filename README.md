# Lloyds E-commerce Android Application - Clean Architecture

This project is a robust and scalable Android e-commerce application built using a **Clean Architecture** approach, **Jetpack Compose** for the UI, and other modern Android development best practices. It demonstrates how to structure an Android app for maintainability, testability, scalability, and robustness. The application is designed to fetch, display, and manage product data, showcasing a well-organized and efficient codebase.

---

## **Overview**

The application provides a comprehensive e-commerce experience, allowing users to browse products, view detailed product information, search for specific items, and navigate through dynamically loaded categories. The project is structured into multiple modules, each with a clear responsibility, following the principles of **Clean Architecture**.

---

## **Key Features**

1. **Clean Architecture:**
   - Separation of concerns into three distinct layers:
      - **Presentation Layer**: Handles UI and user interactions (Jetpack Compose).
      - **Domain Layer**: Contains business logic encapsulated in use cases.
      - **Data Layer**: Manages data sources (APIs, local DB) through repositories.

2. **Jetpack Compose:**
   - Declarative and efficient UI development for modern Android apps.

3. **Dependency Injection:**
   - Hilt simplifies dependency management and improves testability.

4. **Networking:**
   - Retrofit is used for API calls.
   - Gson for JSON serialization/deserialization.

5. **Asynchronous Data Handling:**
   - Kotlin Flows for seamless async data streams.

6. **Dynamic Categories:**
   - Bottom navigation categories are fetched dynamically via an external API.

7. **Search Functionality:**
   - Search products by keywords for an enhanced user experience.

8. **Detailed Product Pages:**
   - Pager integration using **Accompanist Pager** for swiping between product details.

9. **Error Handling:**
   - Robust error management for network, parsing, and empty state scenarios.

10. **Unit Testing:**
   - Ensures app stability using **JUnit**, **Mockito**, and **kotlinx-coroutines-test**.

---

## **Clean Architecture Layers**

The app is divided into three main layers:

```
                          +--------------------------+
                          |      Presentation        |
                          |  Jetpack Compose, ViewModel |
                          +------------+-------------+
                                       |
                          +------------v-------------+
                          |         Domain           |
                          |    Use Cases (Business Logic) |
                          +------------+-------------+
                                       |
                          +------------v-------------+
                          |          Data            |
                          | Repositories, APIs, DB  |
                          +--------------------------+
```

---

## **Data Flow**

Below is the **data flow** diagram representing how data travels through different layers:

```
   +------------------------+
   |        UI (User)       |
   | Jetpack Compose Views  |
   +-----------+------------+
               |
               v
   +-----------v------------+
   |     ViewModel Layer    |
   |   Handles UI State     |
   |   & User Interaction   |
   +-----------+------------+
               |
               v
   +-----------v------------+
   |    Domain Layer        |
   |  Business Logic (Use   |
   |  Cases like FetchData) |
   +-----------+------------+
               |
               v
   +-----------v------------+
   |   Data Layer           |
   | Repository Pattern     |
   | Manages API/DB Sources |
   +-----------+------------+
               |
               v
   +-----------v------------+
   |   External Sources     |
   | (Remote API/Local DB)  |
   +------------------------+
```

### **Detailed Data Flow Steps:**

1. **User Interaction:**
   - The user interacts with the UI (e.g., clicks on a product).
   - UI events are sent to the **ViewModel**.

2. **ViewModel:**
   - The ViewModel processes the event and calls the appropriate **Use Case** in the Domain Layer.
   - Observes and updates the UI state.

3. **Use Case:**
   - The Use Case contains the business logic for the action (e.g., fetch products).
   - It communicates with the **Repository** in the Data Layer.

4. **Repository:**
   - Fetches data either from **Remote APIs** (via Retrofit) or local sources (e.g., Room database).
   - Returns the data back to the Use Case.

5. **ViewModel and UI State:**
   - The ViewModel processes the data and updates the UI state.
   - The Jetpack Compose UI observes this state and re-renders accordingly.

---

## **Project Structure**

The project follows a modular structure:

```
Lloyds-Clean-Architecture-Android/
│
├── app/                          # Main app module
│   ├── di/                       # Dependency Injection (AppModule)
│   ├── presentation/             # UI Composables and ViewModels
│   ├── navigation/               # Jetpack Compose Navigation
│   ├── MainActivity.kt           # App entry point
│   └── build.gradle.kts
│
├── core/                         # Core module
│   ├── data/
│   │   ├── model/                # Data models (Product, Category)
│   │   ├── network/              # Retrofit API services
│   │   └── repository/           # Repository interfaces
│   │
│   ├── domain/
│   │   └── usecase/              # Business logic (Use Cases)
│   └── di/                       # Core DI modules
│
├── features/                     # Feature-specific modules
│   └── assessment/
│       ├── data/                 # Data handling
│       ├── domain/               # Use cases for Assessment
│       ├── presentation/         # Composables and screens
│       └── di/                   # DI for feature module
│
└── build.gradle.kts
```

### **Modules**

1. **`app` Module**:
   - Main application module.
   - Hosts the navigation graph and integrates other modules.
   - **Dependencies:** `core`, `features`, and Jetpack Compose libraries.

2. **`core` Module**:
   - Shared logic across the app (Models, Repositories, Use Cases).
   - Includes:
      - `data/`: Models, network interfaces, repositories.
      - `domain/`: Business logic (Use Cases).
      - `di/`: Dependency Injection with Hilt.

3. **`features` Modules**:
   - Contains feature-specific code (e.g., `assessment`).
   - Organized into `data`, `domain`, and `presentation` layers.

---

## **Technology Stack**

| **Layer**              | **Technologies**              |
|------------------------|-------------------------------|
| **Presentation**       | Jetpack Compose, Navigation  |
| **Domain**             | Kotlin Use Cases             |
| **Data**               | Retrofit, Coil, Kotlin Flows |
| **Dependency Injection** | Hilt                       |
| **Testing**            | JUnit, Mockito, Coroutines   |
| **Build System**       | Gradle Kotlin DSL            |

---

## **Key Components**

1. **Product List Screen**:
   - Displays product lists fetched from API.
   - Uses `LazyColumn` for efficient list rendering.

2. **Product Detail Screen**:
   - Detailed view of a product.
   - Swiping implemented using **Accompanist Pager**.

3. **Dynamic Navigation**:
   - Bottom navigation categories are fetched via API dynamically.

---

## **Unit Testing**

- **Frameworks Used**:
   - **JUnit 5**
   - **Mockito**
   - **kotlinx-coroutines-test**

- **Test Scenarios**:
   - Use cases ensure correct business logic.
   - Repository tests validate data fetching.
   - ViewModel tests verify proper state handling.

---

## **Error Handling**

1. **Network Errors**:
   - Retry logic implemented using Kotlin Flows.
2. **Empty States**:
   - Displayed gracefully using an `EmptyProduct` composable.
3. **Data Parsing Errors**:
   - Handled using Gson with proper error management.

---

## **How to Build and Run**

1. Clone the repository:
   ```bash
   git clone https://github.com/Ramehsudhanapu/LlyodTask.git
   ```
2. Open the project in **Android Studio**.
# output
 ![splash (1)](https://github.com/user-attachments/assets/85b5d1af-8230-436d-9b77-3dbc15af40a5)   ![category (1)](https://github.com/user-attachments/assets/301742b1-606d-4d80-bda2-be5148ff76c5)





 

![product (1)](https://github.com/user-attachments/assets/db2c2cd7-89e9-4843-907e-bd7169b53372) 
![detailproduct (1)](https://github.com/user-attachments/assets/5a092099-dcba-44eb-90af-d5840e52c233)


https://github.com/user-attachments/assets/1385ea7a-da2d-49c0-9d22-7028d80317ea





