package com.mardia.taskmanagementapp.domain.model

import java.util.*

enum class Priority { HIGH, MEDIUM, LOW }
enum class Status { IN_PROGRESS, TODO, DONE }

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val priority: Priority = Priority.LOW,
    val status: Status = Status.TODO,
    val dueDate: String? = null,
    val createdAtEpochMillis: Long = System.currentTimeMillis()
)