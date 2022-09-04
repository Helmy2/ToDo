package com.example.todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.todo.presentation.list.ListRoute
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.dummyRandomList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                val data = dummyRandomList()
                ListRoute(data.first())
            }
        }
    }
}