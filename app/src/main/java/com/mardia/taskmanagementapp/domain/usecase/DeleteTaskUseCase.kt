package com.mardia.taskmanagementapp.domain.usecase

import com.mardia.taskmanagementapp.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repo: TaskRepository) {
    suspend operator fun invoke(taskId: String) = repo.deleteTask(taskId)
}