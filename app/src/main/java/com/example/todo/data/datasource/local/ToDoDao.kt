package com.example.todo.data.datasource.local

import androidx.room.*
import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.data.datasource.local.model.ToDoListDbWithToDoTaskDb
import com.example.todo.data.datasource.local.model.ToDoTaskDb
import com.example.todo.domian.model.ToDoStatus
import com.example.todo.domian.model.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Transaction
    @Query("SELECT * FROM ToDoListDb")
    fun getListWithTask(): Flow<List<ToDoListDbWithToDoTaskDb>>

    @Transaction
    @Query("SELECT * FROM ToDoListDb WHERE list_id = :id")
    fun getListById(id: String): Flow<ToDoListDbWithToDoTaskDb>

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

    @Query("select * from ToDoTaskDb")
    fun getAllTasks(): Flow<List<ToDoTaskDb>>

    @Query("select * from ToDoTaskDb where task_due_date between :from And :to")
    fun getTasksFromTo(from: Long, to: Long): Flow<List<ToDoTaskDb>>

    @Query("select * from ToDoTaskDb where task_due_date > :from")
    fun getTasksFrom(from: Long): Flow<List<ToDoTaskDb>>

}
