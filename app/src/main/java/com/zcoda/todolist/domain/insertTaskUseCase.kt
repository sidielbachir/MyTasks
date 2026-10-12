package com.zcoda.todolist.domain

class InsertTaskUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        taskRepository.insertTask(task)
    }
}