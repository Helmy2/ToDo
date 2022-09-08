package com.example.todo.di

import com.example.todo.data.repository.ToDoRepoImpl
import com.example.todo.domian.repository.ToDoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideToDoRepo(): ToDoRepo = ToDoRepoImpl()
}