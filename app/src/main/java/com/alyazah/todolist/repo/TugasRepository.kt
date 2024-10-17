package com.alyazah.todolist.repo

import androidx.lifecycle.LiveData
import com.alyazah.todolist.dao.TugasDao
import com.alyazah.todolist.entity.Tugas

class TugasRepository(private val tugasDao: TugasDao) {

    fun getTugasByMatkulId(matkulId: Int): LiveData<List<Tugas>> {
        return tugasDao.getTugasByMatkulId(matkulId)
    }

    suspend fun insert(tugas: Tugas) {
        tugasDao.insert(tugas)
    }

    suspend fun delete(tugas: Tugas) {
        tugasDao.delete(tugas)
    }
}
