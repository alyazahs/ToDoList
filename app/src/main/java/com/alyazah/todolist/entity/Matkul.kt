package com.alyazah.todolist.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matkul")
data class Matkul(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val namaMatkul: String,
    val deskripsi: String
)
