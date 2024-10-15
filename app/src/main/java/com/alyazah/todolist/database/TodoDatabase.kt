package com.alyazah.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alyazah.todolist.dao.MatkulDao
import com.alyazah.todolist.dao.TugasDao
import com.alyazah.todolist.dao.UserDao
import com.alyazah.todolist.entity.Matkul
import com.alyazah.todolist.entity.Tugas
import com.alyazah.todolist.entity.User

@Database(entities = [Matkul::class, Tugas::class, User::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun matkulDao(): MatkulDao
    abstract fun tugasDao(): TugasDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
