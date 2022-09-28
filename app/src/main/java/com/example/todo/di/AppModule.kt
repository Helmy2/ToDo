package com.example.todo.di

import com.example.todo.data.datasource.local.LocalManager
import com.example.todo.data.datasource.local.LocalManagerImpl
import com.example.todo.data.datasource.local.ToDoDao
import com.example.todo.data.repository.ToDoRepoImpl
import com.example.todo.data.wrapper.DateTimeProvider
import com.example.todo.data.wrapper.IdProvider
import com.example.todo.domian.repository.ToDoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideToDoRepo(
        localManager: LocalManager,
        dateTimeProvider: DateTimeProvider,
        idProvider: IdProvider
    ): ToDoRepo = ToDoRepoImpl(localManager, dateTimeProvider, idProvider)

    @Singleton
    @Provides
    fun provideLocalManger(toDoDao: ToDoDao): LocalManager =
        LocalManagerImpl(toDoDao)
}