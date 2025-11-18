package com.mardia.taskmanagementapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mardia.taskmanagementapp.presentation.TaskViewModel
import com.mardia.taskmanagementapp.ui.screens.TaskListScreen

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskApplication : Application()
@Composable
fun TaskApp(vm: TaskViewModel) {
    MaterialTheme {
        Surface {
            TaskListScreen(vm)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    Text("Preview")
}
