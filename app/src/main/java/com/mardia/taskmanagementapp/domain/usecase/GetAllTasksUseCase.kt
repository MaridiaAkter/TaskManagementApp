package com.mardia.taskmanagementapp.domain.usecase

import com.mardia.taskmanagementapp.domain.model.Task
import com.mardia.taskmanagementapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetAllTasksUseCase(private val repo: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> = repo.getAllTasks()
}