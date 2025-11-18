package com.mardia.taskmanagementapp.di

import com.mardia.taskmanagementapp.data.local.TaskDao
import com.mardia.taskmanagementapp.data.repository.TaskRepositoryImpl
import com.mardia.taskmanagementapp.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTaskRepository(dao: TaskDao): TaskRepository = TaskRepositoryImpl(dao)
}