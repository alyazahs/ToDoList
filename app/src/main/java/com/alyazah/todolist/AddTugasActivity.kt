package com.alyazah.todolist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.alyazah.todolist.databinding.AddTugasBinding
import com.alyazah.todolist.entity.Tugas
import com.alyazah.todolist.viewmodel.TugasViewModel

class AddTugasActivity : AppCompatActivity() {

    private lateinit var binding: AddTugasBinding
    private val tugasViewModel: TugasViewModel by viewModels()

    // Ambil matkulId dari intent
    private var matkulId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddTugasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Dapatkan matkulId dari intent
        matkulId = intent.getIntExtra("MATKUL_ID", -1)

        binding.btnSubmit.setOnClickListener {
            val namaTugas = binding.edTugas.text.toString().trim()

            if (namaTugas.isNotEmpty() && matkulId != -1) {
                val tugas = Tugas(matkulId = matkulId, namaTugas = namaTugas, isCompleted = false)
                tugasViewModel.insert(tugas)
                Toast.makeText(this, "Tugas berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}