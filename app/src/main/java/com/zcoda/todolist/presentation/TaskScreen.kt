package com.zcoda.todolist.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zcoda.todolist.domain.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(taskViewModel: TaskViewModel) {
    var task by remember { mutableStateOf("") }
    val tasksList by taskViewModel.tasks.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My To-Do List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,
                    titleContentColor = Color.White
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = task,
                    onValueChange = { task = it },
                    label = { Text("Add a task") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(22.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Cyan,
                        unfocusedIndicatorColor = Color.Cyan,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                    )
                )
                Button(
                    onClick = {
                        if (task.isNotBlank()) {
                            taskViewModel.insertTask(Task(title = task))
                            task = ""
                        }
                    },
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(Color.Cyan)
                ){
                    Text("Add")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color.Cyan)
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                items(items=tasksList,key={it.id}){ task ->
                    var isEditing by remember (task.id) { mutableStateOf(false) }
                    var newTitle by remember (task.id) { mutableStateOf(task.title) }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ){
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = task.isDone,
                                onCheckedChange = {
                                    taskViewModel.toggleTaskDone(task)
                                },
                                modifier = Modifier.padding(end = 8.dp),
                                colors = CheckboxDefaults.colors(Color.Cyan)
                            )
                            if (isEditing){
                                OutlinedTextField(
                                    value = newTitle,
                                    onValueChange = { newTitle = it },
                                    label = { Text("Edit task") },
                                    modifier = Modifier.width(160.dp),
                                    shape = RoundedCornerShape(22.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedIndicatorColor = Color.Cyan,
                                        unfocusedIndicatorColor = Color.Cyan,
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                    )
                                )
                                Button(
                                    onClick = {
                                        taskViewModel.editTask(task, newTitle)
                                        isEditing = false
                                    },
                                    modifier = Modifier.padding(start = 8.dp),
                                    shape = RoundedCornerShape(22.dp),
                                    colors = ButtonDefaults.buttonColors(Color.Red)
                                ){
                                    Text("Save")
                                }


                            } else {
                                Text(
                                    text = task.title,
                                    modifier = Modifier.padding(end = 8.dp),
                                    style = if (task.isDone){
                                        LocalTextStyle.current.copy(textDecoration = TextDecoration.LineThrough) }
                                    else {LocalTextStyle.current}
                                )
                            }

                        }
                        Row() {
                            IconButton(
                                onClick = {
                                if (!isEditing)
                                newTitle = task.title
                                isEditing = !isEditing
                                }
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.Red)
                            }
                            IconButton(onClick = { taskViewModel.deleteTask(task) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                            }
                        }

                    }


                }
            }

        }

    }
}