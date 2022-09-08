package com.example.todo.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.data.datasource.local.model.ToDoListDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDoListDb")
    fun getLists(): Flow<List<ToDoListDb>>

    @Query("SELECT * FROM ToDoListDb WHERE list_id = :id")
    fun getListById(id: String): Flow<ToDoListDb>

    @Insert
    suspend fun insertList(data: ToDoListDb)

    @Query("delete from TodoListDb where list_id = :listId")
    suspend fun deleteList(listId: String)

    @Update
    suspend fun updateList(data: ToDoListDb)

}
