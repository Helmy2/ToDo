package com.example.todo.data.datasource.local.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.example.todo.domian.model.ToDoStatus
import java.time.LocalDateTime

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ToDoListDb::class,
            parentColumns = ["list_id"],
            childColumns = ["task_list_id"],
            onDelete = CASCADE
        )
    ],
    indices = [
        Index("task_list_id"),
    ]
)
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
    val dueDate: LocalDateTime?,
    @ColumnInfo(name = "task_completed_at")
    val completedAt: LocalDateTime?,
    @ColumnInfo(name = "task_note", defaultValue = "")
    val note: String = "",
    @ColumnInfo(name = "task_created_at")
    val createdAt: LocalDateTime?,
    @ColumnInfo(name = "task_updated_at")
    val updatedAt: LocalDateTime?,
)