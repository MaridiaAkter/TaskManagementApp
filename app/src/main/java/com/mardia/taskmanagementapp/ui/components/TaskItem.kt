package com.mardia.taskmanagementapp.ui.components

import com.mardia.taskmanagementapp.domain.model.Task
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon


@Composable
fun TaskItem(
    task: Task,
    onToggleStatus: (Task) -> Unit,
    onEdit: (Task) -> Unit,
    onDelete: (String) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth().padding(6.dp)) {
        Row(modifier = Modifier.padding(12.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(task.title, fontWeight = FontWeight.Bold)
                task.description?.let { Text(it) }
                Text("Priority: ${task.priority}")
                Text("Status: ${task.status}")
                Text("Duedate: ${task.dueDate}")
            }
            // Actions Column
            Column {

                // Toggle Status
                IconButton(onClick = { onToggleStatus(task) }) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Toggle Status",
                        modifier = Modifier.size(28.dp)   // 👈 Increase icon size
                    )
                }

                // Edit Task
                IconButton(onClick = { onEdit(task) }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit Task",
                        modifier = Modifier.size(28.dp)
                    )
                }

                // Delete Task
                IconButton(onClick = { onDelete(task.id) }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Task",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}
