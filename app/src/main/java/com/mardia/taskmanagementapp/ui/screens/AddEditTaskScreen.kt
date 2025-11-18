package com.mardia.taskmanagementapp.ui.screens

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.mardia.taskmanagementapp.domain.model.Priority
import com.mardia.taskmanagementapp.domain.model.Task
import com.mardia.taskmanagementapp.ui.components.DatePickerField
import com.mardia.taskmanagementapp.ui.components.DropdownField
import com.mardia.taskmanagementapp.ui.components.TextField
import com.mardia.taskmanagementapp.utils.DateUtils
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun AddEditTaskScreen(
    task: Task? = null,
    onDismiss: () -> Unit,
    onSave: (title: String, description: String?, priority: Priority, dueDate: String?) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(Priority.LOW) }
    var dueDate by remember {mutableStateOf(value = "")}

    LaunchedEffect(key1 = task) {
        if (task != null) {
            title = task.title
            desc = task.description ?: ""
            priority = task.priority
            dueDate = task.dueDate ?: ""
        }
    }

    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (task != null) "Edit Task" else "Add Task") },
        text = {
            Column {
                TextField(
                    title = "Title*",
                    value = title,
                    onValueChange = { title = it }
                )
                TextField(
                    title = "Description",
                    value = desc,
                    onValueChange = { desc = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
                DropdownField(
                    label = "Priority",
                    items = Priority.entries.map { it.name.lowercase().replaceFirstChar { char -> char.uppercase() } },
                    selectedItem = priority.name.lowercase().replaceFirstChar { it.uppercase() },
                    onItemSelected = { selectedItem ->
                        priority = Priority.valueOf(selectedItem.uppercase())
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                DatePickerField(
                    label = "Due Date",
                    initialDate = dueDate,
                    onDateSelected = { dueDate = it }
                )
            }
        },

        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        onSave(title.trim(), desc.ifBlank { null }, priority, dueDate)
                    } else {
                        Toast.makeText(context, "Title cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                }
            ) { Text(if (task != null) "Update" else "Save") }
        },
        dismissButton = { Button(onClick = onDismiss) { Text("Cancel") } },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    )
}