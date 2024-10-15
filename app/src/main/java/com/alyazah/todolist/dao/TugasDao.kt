package com.alyazah.todolist.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alyazah.todolist.entity.Tugas

@Dao
interface TugasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTugas(tugas: Tugas)

    @Query("SELECT * FROM tugas_table")
    fun getAllTugas(): LiveData<List<Tugas>>
}
