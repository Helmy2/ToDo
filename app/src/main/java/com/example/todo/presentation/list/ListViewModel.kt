package com.example.todo.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.repository.ToDoRepo
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repo: ToDoRepo,
) : ViewModel() {
    fun getToDoList(id: String): Flow<ToDoList> {
        return repo.getToDoList(id)
    }
}