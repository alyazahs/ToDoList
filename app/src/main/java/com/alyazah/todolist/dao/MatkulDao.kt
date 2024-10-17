package com.alyazah.todolist.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alyazah.todolist.entity.Matkul

@Dao
interface MatkulDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(matkul: Matkul)

    @Update
    suspend fun update(matkul: Matkul)

    @Delete
    suspend fun delete(matkul: Matkul)

    @Query("SELECT * FROM matkul ORDER BY namaMatkul ASC")
    fun getAllMatkuls(): LiveData<List<Matkul>>

    @Query("SELECT * FROM matkul WHERE userId = :userId ORDER BY namaMatkul ASC")
    fun getMatkulByUserId(userId: Int): LiveData<List<Matkul>>

    @Query("SELECT * FROM matkul WHERE id = :matkulId LIMIT 1")
    fun getMatkulById(matkulId: Int): LiveData<Matkul>
}
