package com.mardia.taskmanagementapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mardia.taskmanagementapp.domain.model.Task
import com.mardia.taskmanagementapp.domain.usecase.AddTaskUseCase
import com.mardia.taskmanagementapp.domain.usecase.DeleteTaskUseCase
import com.mardia.taskmanagementapp.domain.usecase.GetAllTasksUseCase
import com.mardia.taskmanagementapp.domain.usecase.UpdateTaskUseCase
import com.mardia.taskmanagementapp.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TasksUiState(
    val tasks: List<Task> = emptyList(),
    val searchQuery: String = ""
)
@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getAllTasks: GetAllTasksUseCase,
    private val addTask: AddTaskUseCase,
    private val updateTask: UpdateTaskUseCase,
    private val deleteTask: DeleteTaskUseCase
) : ViewModel() {

    // Base flow of all tasks from DB
    private val allTasksFlow = getAllTasks()

    // UI State: apply search via transformation
    val uiState: StateFlow<TasksUiState> = allTasksFlow
        .map { TasksUiState(tasks = it) }
        .stateIn(viewModelScope, SharingStarted.Lazily, TasksUiState())
    fun addNewTask(task: Task) = viewModelScope.launch { addTask(task) }
    fun updateExisting(task: Task) = viewModelScope.launch { updateTask(task) }
    fun deleteById(id: String) = viewModelScope.launch { deleteTask(id) }

    // For simple search we'll expose helper returning Flow, but UI can filter locally as well.
    fun search(query: String) = getAllTasks().map { list -> list.filter { it.title.contains(query, ignoreCase = true) } }

    fun getTaskSortBy(sortBy: String) =
        when (sortBy) {
            "Date" -> getAllTasks().map { list -> list.sortedByDescending { DateUtils.dateToMillis(it.dueDate ?: "") } }
            "Priority" -> getAllTasks().map { list -> list.sortedBy { it.priority } }
            "Status" -> getAllTasks().map { list -> list.sortedBy { it.status } }
            else -> getAllTasks()
        }
}