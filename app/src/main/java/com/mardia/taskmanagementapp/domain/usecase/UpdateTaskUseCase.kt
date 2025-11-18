package com.mardia.taskmanagementapp.domain.usecase

import com.mardia.taskmanagementapp.domain.model.Task
import com.mardia.taskmanagementapp.domain.repository.TaskRepository

class UpdateTaskUseCase(private val repo: TaskRepository) {
    suspend operator fun invoke(task: Task) = repo.updateTask(task)
}