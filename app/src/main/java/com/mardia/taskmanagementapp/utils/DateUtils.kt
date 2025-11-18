package com.mardia.taskmanagementapp.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {

    const val DATE_DD_MM_YYYY = "dd/MM/yyyy"
    const val DATE_DD_MMM_YYYY = "dd MMM yyyy"

    fun dateToMillis(dateString: String, pattern: String = DATE_DD_MMM_YYYY): Long? {
        return try {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            val date = sdf.parse(dateString)
            date?.time
        } catch (e: Exception) {
            null
        }
    }

    fun millisToDate(millis: Long?, pattern: String = DATE_DD_MMM_YYYY): String? {
        return try {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            sdf.format(millis)
        } catch (e: Exception) {
            null
        }
    }
}

