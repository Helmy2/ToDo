package com.example.todo.data.datasource.local

import androidx.room.*
import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.data.datasource.local.model.ToDoListDbWithToDoTaskDb
import com.example.todo.data.datasource.local.model.ToDoTaskDb
import com.example.todo.domian.model.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDoListDb")
    fun getLists(): Flow<List<ToDoListDb>>

    @Transaction
    @Query("SELECT * FROM ToDoListDb WHERE list_id = :id")
    fun getListById(id: String): Flow<ToDoListDbWithToDoTaskDb>

    @Insert
    suspend fun insertList(data: ToDoListDb)

    @Query("delete from TodoListDb where list_id = :listId")
    suspend fun deleteList(listId: String)

    @Update
    suspend fun updateList(data: ToDoListDb)

    @Transaction
    @Query("SELECT * FROM ToDoListDb")
    fun getToDoListWithToDoTask(): Flow<List<ToDoListDbWithToDoTaskDb>>

    @Insert
    suspend fun insertToDoTask(data: ToDoTaskDb)

}
