package com.example.todo.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.data.datasource.local.model.ToDoTaskDb
import kotlinx.coroutines.DelicateCoroutinesApi

@Database(
    entities = [
        ToDoListDb::class,
        ToDoTaskDb::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao

    @DelicateCoroutinesApi
    companion object {
        private const val TODO_DB_NAME = "todo-db"

        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getInstance(context: Context): ToDoDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): ToDoDatabase {
            val db = Room.databaseBuilder(
                context,
                ToDoDatabase::class.java,
                TODO_DB_NAME
            ).fallbackToDestructiveMigration()

            return db.build()
        }
    }
}
