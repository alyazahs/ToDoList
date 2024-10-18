package com.alyazah.todolist.repo

import androidx.lifecycle.LiveData
import com.alyazah.todolist.dao.MatkulDao
import com.alyazah.todolist.entity.Matkul

class MatkulRepository(private val matkulDao: MatkulDao) {

    fun getAllMatkuls(): LiveData<List<Matkul>> {
        return matkulDao.getAllMatkuls()
    }

    fun getMatkulByUserId(userId: Int): LiveData<List<Matkul>> {
        return matkulDao.getMatkulByUserId(userId)
    }

    fun getMatkulById(matkulId: Int): LiveData<Matkul> {
        return matkulDao.getMatkulById(matkulId)
    }

    suspend fun insert(matkul: Matkul) {
        matkulDao.insert(matkul)
    }

    suspend fun update(matkul: Matkul) {
        matkulDao.update(matkul)
    }

    suspend fun delete(matkul: Matkul) {
        matkulDao.delete(matkul)
    }
}
