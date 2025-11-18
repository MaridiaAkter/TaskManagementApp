package com.mardia.taskmanagementapp.data.local

import androidx.room.TypeConverter
import com.mardia.taskmanagementapp.domain.model.Priority
import com.mardia.taskmanagementapp.domain.model.Status


class Converters {
    @TypeConverter
    fun fromPriority(value: Priority) = value.ordinal
    @TypeConverter
    fun toPriority(value: Int) = Priority.values()[value]

    @TypeConverter
    fun fromStatus(value: Status) = value.ordinal
    @TypeConverter
    fun toStatus(value: Int) = Status.values()[value]
}