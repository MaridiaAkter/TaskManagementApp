package com.mardia.taskmanagementapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String?,
    val priority: Int, // 0 low,1 med,2 high
    val status: Int,   // 0 to-do,1 inprogress,2 done
    val dueDateInMillis: Long?,
    val createdAt: Long
)