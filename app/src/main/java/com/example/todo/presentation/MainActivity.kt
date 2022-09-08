package com.example.todo.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.presentation.home.HomeRoute
import com.example.todo.presentation.list.ListRoute
import com.example.todo.presentation.navigation.TodoNavHost
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.dummyRandomList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            ToDoTheme {
                TodoNavHost()
            }
        }
    }
}
