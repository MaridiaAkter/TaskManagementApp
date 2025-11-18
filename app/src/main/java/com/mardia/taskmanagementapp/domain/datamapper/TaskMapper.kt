package com.mardia.taskmanagementapp.domain.datamapper

import com.mardia.taskmanagementapp.data.model.TaskEntity
import com.mardia.taskmanagementapp.domain.model.Priority
import com.mardia.taskmanagementapp.domain.model.Status
import com.mardia.taskmanagementapp.domain.model.Task
import com.mardia.taskmanagementapp.utils.DateUtils

fun TaskEntity.toDomain(): Task = Task(
    id = this.id,
    title = this.title,
    description = this.description,
    priority = when (this.priority) {
        0 -> Priority.HIGH
        1 -> Priority.MEDIUM
        2 -> Priority.LOW
        else -> Priority.LOW
    },
    status = when (this.status) {
        0 -> Status.IN_PROGRESS
        1 -> Status.TODO
        2 -> Status.DONE
        else -> Status.TODO
    },
    dueDate = DateUtils.millisToDate(this.dueDateInMillis),
    createdAtEpochMillis = this.createdAt
)

fun Task.toEntity(): TaskEntity = TaskEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    priority = this.priority.ordinal,
    status = this.status.ordinal,
    dueDateInMillis = DateUtils.dateToMillis(this.dueDate ?: ""),
    createdAt = this.createdAtEpochMillis
)