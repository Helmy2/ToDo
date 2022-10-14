package com.example.todo.data.datasource.local

import android.app.appsearch.StorageInfo
import androidx.room.*
import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.data.datasource.local.model.ToDoListDbWithToDoTaskDb
import com.example.todo.data.datasource.local.model.ToDoTaskDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Transaction
    @Query("SELECT * FROM ToDoListDb")
    fun getListWithTask(): Flow<List<ToDoListDbWithToDoTaskDb>>

    @Transaction
    @Query("SELECT * FROM ToDoListDb WHERE list_id = :id")
    fun getListWithTaskById(id: String): Flow<ToDoListDbWithToDoTaskDb>

    @Query("SELECT * FROM ToDoListDb WHERE list_id = :id")
    fun getListById(id: String): ToDoListDb

    @Query("SELECT * FROM ToDoTaskDb WHERE task_name LIKE :value")
    fun search(value: String): Flow<List<ToDoTaskDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(data: ToDoListDb)

    @Query("delete from TodoListDb where list_id = :listId")
    suspend fun deleteList(listId: String)

    @Update
    suspend fun updateList(data: ToDoListDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(data: ToDoTaskDb)

    @Query("delete from ToDoTaskDb where task_id = :id")
    suspend fun deleteTask(id: String)

    @Update
    suspend fun updateTask(toDoTaskDb: ToDoTaskDb)

}
