package com.alyazah.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alyazah.todolist.database.TodoDatabase
import com.alyazah.todolist.entity.Tugas
import com.alyazah.todolist.repo.TugasRepository
import kotlinx.coroutines.launch

class TugasViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TugasRepository
    val allTugas: LiveData<List<Tugas>>

    init {
        val tugasDao = TodoDatabase.getDatabase(application).tugasDao()
        repository = TugasRepository(tugasDao)
        allTugas = repository.getTugasByMatkulId(-1) // Inisialisasi dengan nilai dummy
    }

    fun getTugasByMatkulId(matkulId: Int): LiveData<List<Tugas>> {
        return repository.getTugasByMatkulId(matkulId)
    }

    fun insert(tugas: Tugas) = viewModelScope.launch {
        repository.insert(tugas)
    }

    fun delete(tugas: Tugas) = viewModelScope.launch {
        repository.delete(tugas)
    }
}
