package com.zcoda.todolist.domain

import kotlinx.coroutines.flow.Flow

class GetAllTasksUseCase(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> {
        return taskRepository.getAllTasks()
    }
}