package com.mardia.taskmanagementapp.di

import com.mardia.taskmanagementapp.domain.repository.TaskRepository
import com.mardia.taskmanagementapp.domain.usecase.AddTaskUseCase
import com.mardia.taskmanagementapp.domain.usecase.DeleteTaskUseCase
import com.mardia.taskmanagementapp.domain.usecase.GetAllTasksUseCase
import com.mardia.taskmanagementapp.domain.usecase.UpdateTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides fun provideGetAllTasks(repo: TaskRepository) = GetAllTasksUseCase(repo)
    @Provides fun provideAddTask(repo: TaskRepository) = AddTaskUseCase(repo)
    @Provides fun provideUpdateTask(repo: TaskRepository) = UpdateTaskUseCase(repo)
    @Provides fun provideDeleteTask(repo: TaskRepository) = DeleteTaskUseCase(repo)
}
