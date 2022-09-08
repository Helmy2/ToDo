package com.example.todo.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo.domian.model.ToDoColor

@Entity
data class ToDoListDb(
    @PrimaryKey
    @ColumnInfo(name = "list_id")
    val id: String,
    @ColumnInfo(name = "list_name")
    val name: String,
    @ColumnInfo(name = "list_color")
    val color: ToDoColor,
    @ColumnInfo(name = "list_created_at")
    val createdAt: Long,
    @ColumnInfo(name = "list_updated_at")
    val updatedAt: Long,
)

