package com.zcoda.todolist.data

import com.zcoda.todolist.domain.Task

// Converts Database Entity -> Domain Model
fun TaskEntity.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        isDone = isDone
    )
}

// Converts Domain Model -> Database Entity
fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        isDone = isDone
    )
}