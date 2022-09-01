package com.example.todo.di

import com.example.todo.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object AppModule {
    val module = module {
        viewModel { HomeViewModel() }
    }
}