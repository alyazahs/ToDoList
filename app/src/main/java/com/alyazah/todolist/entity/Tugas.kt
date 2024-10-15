package com.alyazah.todolist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tugas_table")
data class Tugas(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nama_tugas") val nama: String,
    @ColumnInfo(name = "status_tugas") val status: Boolean
)
