# Task Management App
A simple and modern **Task Management App** built using **Kotlin**, **Jetpack Compose**, **MVVM architecture** and **SOLID principles**. This app allows users to create, update, delete, and manage tasks with statuses and due dates.

---

## 🚀 Features
- Add new tasks
- Edit existing tasks
- Delete tasks
- Change task status
- Set task due dates
- Search by Title
- Sort By Date, Priority (High->Medium->Low), Status (In-Progress->ToDo->Done)
- Modern UI using **Jetpack Compose**
- Clean architecture with **MVVM** and **SOLID principles**
- Local data storage (Room Database)

---

---

## 🎥 Demo Video
Here is the demo video of the app:

---

## 🛠️ Tech Stack
### **Programming Language**
- Kotlin

### **UI Framework**
- Jetpack Compose
- Material 3

### **Dependency Injection**
- Hilt DI

### **Architecture**
- MVVM
- SOLID Principles
- Clean Architecture approach
- StateFlow

### **Database**
- Room Database

---

## 📂 Project Structure
```
com.mardia.taskmanagementapp
│
├── data
│   ├── local
│   ├── model
│   └── repository
│
├── di
│   ├── DatabaseModule.kt
│   ├── RepositoryModule.kt
│   └── UseCaseModule.kt
│
├── domain
│   ├── datamapper
│   ├── model
│   ├── repository
│   └── usecase
│
├── navigation
│   └── Screen.kt
│
├── presentation
│   └── TaskViewModel.kt
│
├── ui
│   ├── components
│   ├── screens
│   └── theme
│
├── utils
│   └── DateUtils.kt
│
├── MainActivity.kt
└── TaskApp.kt
```

## 📱 Screens
- **Task List Screen:** Displays all tasks
- **Add Task Screen:** Create new task
- **Edit Task Screen:** Update task with pre-filled values
- **Dialog for quick edits**

---

## ▶️ How to Run
1. Clone the repository:
```
git clone https://github.com/MaridiaAkter/taskmanagementapp.git
```
2. Open the project in **Android Studio**
3. Sync Gradle
4. Run on an emulator or physical device

---

## 📌 Future Improvements
- Add notifications for due tasks
- Add cloud sync

---

