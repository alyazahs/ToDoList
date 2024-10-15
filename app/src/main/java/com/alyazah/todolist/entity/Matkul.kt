package com.alyazah.todolist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matkul_table")
data class Matkul(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nama_matkul") val nama: String,
    @ColumnInfo(name = "deskripsi_matkul") val deskripsi: String
)
