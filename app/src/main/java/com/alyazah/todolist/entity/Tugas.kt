package com.alyazah.todolist.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tugas")
data class Tugas(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val matkulId: Int,
    val namaTugas: String,
    val isCompleted: Boolean
)
