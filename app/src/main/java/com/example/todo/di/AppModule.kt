package com.example.todo.di

import com.example.todo.data.repository.ToDoRepoImpl
import com.example.todo.domian.repository.ToDoRepo
import com.example.todo.presentation.list.ListViewModel
import com.example.todo.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


object AppModule {
    val module = module {
        viewModel {
            HomeViewModel(get())
        }
        viewModel {
            ListViewModel(get())
        }
        singleOf<ToDoRepo> { ToDoRepoImpl() }
    }
}