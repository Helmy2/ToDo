package com.example.todo.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.presentation.home.HomeRoute
import com.example.todo.presentation.list.ListRoute
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.dummyRandomList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            ToDoTheme {
                val data = dummyRandomList()
//                ListRoute(data.first())
                HomeRoute()
            }
        }
    }
}
