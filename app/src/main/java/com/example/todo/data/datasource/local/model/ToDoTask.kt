package com.example.todo.data.datasource.local.model

import androidx.room.*
import com.example.todo.domian.model.ToDoStatus

@Entity
data class ToDoTaskDb(
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    val id: String,
    @ColumnInfo(name = "task_name")
    val name: String,
    @ColumnInfo(name = "task_list_id")
    val listId: String,
    @ColumnInfo(name = "task_status")
    val status: ToDoStatus,
    @ColumnInfo(name = "task_due_date")
    val dueDate: Long?,
    @ColumnInfo(name = "task_completed_at")
    val completedAt: Long?,
    @ColumnInfo(name = "task_is_due_date_time_set")
    val isDueDateTimeSet: Boolean,
    @ColumnInfo(name = "task_note", defaultValue = "")
    val note: String = "",
    @ColumnInfo(name = "task_created_at")
    val createdAt: Long,
    @ColumnInfo(name = "task_updated_at")
    val updatedAt: Long,
)