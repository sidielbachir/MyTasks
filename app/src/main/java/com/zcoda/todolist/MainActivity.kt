package com.zcoda.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zcoda.todolist.presentation.TaskScreen
import com.zcoda.todolist.presentation.TaskViewModel
import com.zcoda.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {

    private val taskViewModel: TaskViewModel by viewModels<TaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface{
                    TaskScreen(taskViewModel = taskViewModel)
                }
            }

        }
    }
}

