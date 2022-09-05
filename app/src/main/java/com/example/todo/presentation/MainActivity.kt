package com.example.todo.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.presentation.list.ListRoute
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.dummyRandomList
import java.time.temporal.Temporal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            ToDoTheme {
                val data = dummyRandomList()
                ListRoute(data.first())
            }
        }
    }
}
