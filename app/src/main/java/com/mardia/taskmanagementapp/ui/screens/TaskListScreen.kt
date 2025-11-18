package com.mardia.taskmanagementapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mardia.taskmanagementapp.domain.model.Priority
import com.mardia.taskmanagementapp.domain.model.Status
import com.mardia.taskmanagementapp.domain.model.Task
import com.mardia.taskmanagementapp.presentation.TaskViewModel
import com.mardia.taskmanagementapp.ui.components.DropdownField
import com.mardia.taskmanagementapp.ui.components.TaskItem
import com.mardia.taskmanagementapp.ui.components.TextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf("") }
    var sortBy by remember { mutableStateOf("") }
    var showAdd by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var tasks by remember { mutableStateOf(emptyList<Task>()) }
    var editingTask by remember { mutableStateOf<Task?>(null) }


    // Collect search results when query changes
    LaunchedEffect(query) {
        if (query.isBlank()) {
            viewModel.uiState.collect { state -> tasks = state.tasks }
        } else {
            viewModel.search(query).collectLatest { filtered -> tasks = filtered }
        }
    }

    LaunchedEffect(sortBy) {
        if (sortBy.isBlank()) {
            viewModel.uiState.collect { state -> tasks = state.tasks }
        } else {
            viewModel.getTaskSortBy(sortBy).collectLatest { filtered -> tasks = filtered }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Task Manager") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAdd = true }) { Text("+") }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(12.dp)
        ) {
            TextField(
                title = "Search by title",
                value = query,
                onValueChange = { query = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
            DropdownField(
                label = "Sort By",
                items = listOf("Date", "Priority", "Status"),
                selectedItem = sortBy,
                onItemSelected = { selectedItem ->
                    sortBy = selectedItem
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(tasks) { t ->
                    TaskItem(
                        task = t,
                        onToggleStatus = { task ->
                            val next = when (task.status) {
                                Status.TODO -> task.copy(status = Status.IN_PROGRESS)
                                Status.IN_PROGRESS -> task.copy(status = Status.DONE)
                                Status.DONE -> task.copy(status = Status.TODO)
                            }
                            viewModel.updateExisting(next)
                        },
                        onEdit = { task ->
                            editingTask = task   //  Set the task you want to edit
                            showAdd = true       //  Open dialog
                        },
                        onDelete = { id -> viewModel.deleteById(id) }
                    )
                }
            }
        }
    }

    if (showAdd) {
        AddEditTaskScreen(
            task = editingTask,   //  Pass task for editing
            onDismiss = {
                showAdd = false
                editingTask = null
            },
            onSave = { title, desc, priority, dueDate ->
                if (editingTask == null) {
                    // ADD new task
                    val task = Task(
                        title = title,
                        description = desc,
                        priority = priority,
                        dueDate = dueDate
                    )
                    scope.launch { viewModel.addNewTask(task) }
                } else {
                    // UPDATE existing task
                    val updated = editingTask!!.copy(
                        title = title,
                        description = desc,
                        priority = priority,
                        dueDate = dueDate
                    )
                    scope.launch { viewModel.updateExisting(updated) }
                }
                showAdd = false
                editingTask = null
            }
        )
    }

}
