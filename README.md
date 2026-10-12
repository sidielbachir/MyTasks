# MyTasks 📝

A modern, reactive Android application built with **Jetpack Compose**, **Room Database**, and **Clean Architecture** principles.

---

## 🏗️ Architecture

This project strictly adheres to **Clean Architecture** principles, separating concerns into distinct layers to ensure maintainability, testability, and scalability.

```
com.zcoda.todolist/
│
├── data/               # Data Layer (Room Database, Entities, Daos, Repositories)
│   ├── TaskDao.kt
│   ├── TaskDatabase.kt
│   ├── TaskDataRepository.kt
│   ├── TaskEntity.kt
│   └── TaskMapper.kt
│
├── domain/             # Domain Layer (Pure Business Logic & Models)
│   ├── Task.kt
│   ├── TaskRepository.kt
│   ├── GetAllTasksUseCase.kt
│   ├── InsertTaskUseCase.kt
│   ├── UpdateTaskUseCase.kt
│   └── DeleteTaskUseCase.kt
│
└── presentation/       # Presentation Layer (UI & ViewModels)
    ├── TaskScreen.kt
    └── TaskViewModel.kt
```

---

## 🚀 Tech Stack & Libraries

* **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material 3.
* **Architecture**: MVVM (Model-View-ViewModel) + Clean Architecture.
* **Database**: [Room](https://developer.android.com/training/data-storage/room) with **KSP** (Kotlin Symbol Processing).
* **Asynchronous Programming**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & **Flow** (`StateFlow`, `collectAsStateWithLifecycle`).
* **Lifecycle Management**: Android Lifecycle ViewModel & Compose Lifecycle Integration.

---

## ✨ Features

* **Reactive Data Stream**: Automatically observes database changes using Room `Flow` combined with `collectAsStateWithLifecycle()`.
* **Add Tasks**: Input new tasks with real-time validation.
* **Toggle Completion**: Mark tasks as completed/uncompleted with strikethrough text styling.
* **Inline Editing**: Seamlessly edit task titles directly within the list.
* **Delete Tasks**: Remove tasks instantly from local storage.

---

## 🛠️ Getting Started

### Prerequisites
* Android Studio Ladybug / Koala (or newer)
* JDK 11 or higher
* Android SDK (API 28+)

### Installation & Running
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/ToDoList.git
   ```
2. Open the project in **Android Studio**.
3. Let Gradle sync dependencies.
4. Run the app on an Android Emulator or physical device (`Shift + F10`).

---

## 📄 License

```
Copyright (c) 2026 Zcoda. All rights reserved.
```
