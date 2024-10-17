package com.alyazah.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alyazah.todolist.database.TodoDatabase
import com.alyazah.todolist.entity.Matkul
import com.alyazah.todolist.repo.MatkulRepository
import kotlinx.coroutines.launch

class MatkulViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MatkulRepository
    val allMatkuls: LiveData<List<Matkul>>

    init {
        val matkulDao = TodoDatabase.getDatabase(application).matkulDao()
        repository = MatkulRepository(matkulDao)
        allMatkuls = repository.getAllMatkuls()
    }

    fun getMatkulByUserId(userId: Int): LiveData<List<Matkul>> {
        return repository.getMatkulByUserId(userId)
    }

    fun getMatkulById(matkulId: Int): LiveData<Matkul> {
        return repository.getMatkulById(matkulId)
    }

    fun insert(matkul: Matkul) = viewModelScope.launch {
        repository.insert(matkul)
    }

    fun update(matkul: Matkul) = viewModelScope.launch {
        repository.update(matkul)
    }

    fun delete(matkul: Matkul) = viewModelScope.launch {
        repository.delete(matkul)
    }
}
