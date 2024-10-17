package com.alyazah.todolist.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alyazah.todolist.entity.Tugas

@Dao
interface TugasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tugas: Tugas)

    @Delete
    suspend fun delete(tugas: Tugas)

    @Query("SELECT * FROM tugas WHERE matkulId = :matkulId ORDER BY namaTugas ASC")
    fun getTugasByMatkulId(matkulId: Int): LiveData<List<Tugas>>
}
