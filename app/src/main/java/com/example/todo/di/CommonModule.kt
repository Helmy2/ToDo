package com.example.todo.di

import com.example.todo.data.wrapper.DateTimeProvider
import com.example.todo.data.wrapper.DateTimeProviderImpl
import com.example.todo.data.wrapper.IdProvider
import com.example.todo.data.wrapper.IdProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun provideIdProvider(): IdProvider {
        return IdProviderImpl()
    }

    @Singleton
    @Provides
    fun provideDateTimeProvider(): DateTimeProvider {
        return DateTimeProviderImpl()
    }
}
