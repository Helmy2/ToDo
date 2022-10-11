package com.example.todo.di

import android.content.Context
import com.example.todo.data.datasource.local.ToDoDao
import com.example.todo.data.datasource.local.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Singleton

@DelicateCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideToDoReadDao(@ApplicationContext context: Context): ToDoDao {
        return ToDoDatabase.getInstance(context).toDoDao()
    }

}
