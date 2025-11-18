package com.mardia.taskmanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.mardia.taskmanagementapp.presentation.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val vm: TaskViewModel = hiltViewModel()
            TaskApp(vm)
        }
    }
}

/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Manual DI wiring:
        val db = AppDatabase.create(applicationContext)
        val repo = TaskRepositoryImpl(db.taskDao())
        val getTasksUseCase = GetTasksUseCase(repo)
        val addTaskUseCase = AddTaskUseCase(repo)
        val updateTaskUseCase = UpdateTaskUseCase(repo)
        val deleteTaskUseCase = DeleteTaskUseCase(repo)

        setContent {
            val vm: TaskViewModel = viewModel(factory = object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TaskViewModel(getTasksUseCase, addTaskUseCase, updateTaskUseCase, deleteTaskUseCase) as T
                }
            })
            TaskApp(vm)
        }
    }
}*/

