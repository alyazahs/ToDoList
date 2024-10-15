package com.alyazah.todolist.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alyazah.todolist.entity.Matkul

@Dao
interface MatkulDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatkul(matkul: Matkul)

    @Query("SELECT * FROM matkul_table")
    fun getAllMatkul(): LiveData<List<Matkul>>
}
