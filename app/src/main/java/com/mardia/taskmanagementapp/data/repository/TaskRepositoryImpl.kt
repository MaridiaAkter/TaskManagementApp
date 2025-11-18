package com.mardia.taskmanagementapp.data.repository

import com.mardia.taskmanagementapp.data.local.TaskDao
import com.mardia.taskmanagementapp.data.model.TaskEntity
import com.mardia.taskmanagementapp.domain.datamapper.toDomain
import com.mardia.taskmanagementapp.domain.datamapper.toEntity
import com.mardia.taskmanagementapp.domain.model.Priority
import com.mardia.taskmanagementapp.domain.model.Status
import com.mardia.taskmanagementapp.domain.model.Task
import com.mardia.taskmanagementapp.domain.repository.TaskRepository
import com.mardia.taskmanagementapp.utils.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val dao: TaskDao) : TaskRepository {

    override fun getAllTasks(): Flow<List<Task>> = dao.getAll().map { list -> list.map { it.toDomain() } }

    override fun searchTasks(query: String): Flow<List<Task>> {
        val pattern = "%${query.replace("%", "\\%")}%"
        return dao.searchByTitle(pattern).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun addTask(task: Task) = dao.insert(task.toEntity())

    override suspend fun updateTask(task: Task) = dao.update(task.toEntity())

    override suspend fun deleteTask(taskId: String) = dao.deleteById(taskId)
}