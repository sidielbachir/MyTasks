package com.zcoda.todolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.zcoda.todolist.data.TaskDataRepository
import com.zcoda.todolist.data.TaskDatabase
import com.zcoda.todolist.domain.DeleteTaskUseCase
import com.zcoda.todolist.domain.GetAllTasksUseCase
import com.zcoda.todolist.domain.InsertTaskUseCase
import com.zcoda.todolist.domain.Task
import com.zcoda.todolist.domain.UpdateTaskUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class TaskViewModel(application: Application) : AndroidViewModel(application) {

    // Ideally, in a larger app, these would be injected using Hilt/DI.
    private val db = Room.databaseBuilder(
        application,
        TaskDatabase::class.java,
        "tasks.db"
    ).build()

    private val taskRepository = TaskDataRepository(db.taskDao())

    // Use Cases
    private val getAllTasksUseCase = GetAllTasksUseCase(taskRepository)
    private val insertTaskUseCase = InsertTaskUseCase(taskRepository)
    private val updateTaskUseCase = UpdateTaskUseCase(taskRepository)
    private val deleteTaskUseCase = DeleteTaskUseCase(taskRepository)

    // Exposed StateFlow using GetAllTasksUseCase
    val tasks: StateFlow<List<Task>> = getAllTasksUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertTask(task: Task) {
        viewModelScope.launch {
            insertTaskUseCase(task)
        }
    }

    fun toggleTaskDone(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase(task.copy(isDone = !task.isDone))
        }
    }

    fun editTask(task: Task, newTitle: String) {
        viewModelScope.launch {
            if (newTitle.isNotBlank()) {
                updateTaskUseCase(task.copy(title = newTitle))
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }
}