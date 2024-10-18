package com.alyazah.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alyazah.todolist.adapter.TugasAdapter
import com.alyazah.todolist.databinding.ActivityTugasBinding
import com.alyazah.todolist.entity.Tugas
import com.alyazah.todolist.viewmodel.MatkulViewModel
import com.alyazah.todolist.viewmodel.TugasViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TugasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTugasBinding
    private val tugasViewModel: TugasViewModel by viewModels()
    private val matkulViewModel: MatkulViewModel by viewModels()
    private lateinit var adapter: TugasAdapter
    private var matkulId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTugasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        matkulId = intent.getIntExtra("MATKUL_ID", -1)

        adapter = TugasAdapter(
            onDeleteClick = { tugas -> deleteTugas(tugas) }
        )

        binding.rvTugas.layoutManager = LinearLayoutManager(this)
        binding.rvTugas.adapter = adapter

        tugasViewModel.getTugasByMatkulId(matkulId).observe(this) { tugasList ->
            tugasList?.let {
                adapter.setTugas(it)
            }
        }

        matkulViewModel.getMatkulById(matkulId).observe(this) { matkul ->
            matkul?.let {
                binding.tvName.text = it.namaMatkul
            }
        }

        val currentDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())
        binding.tvTime.text = currentDate

        binding.btnTugas.setOnClickListener {
            val intent = Intent(this, AddTugasActivity::class.java)
            intent.putExtra("MATKUL_ID", matkulId) // Kirim matkulId ke AddTugasActivity
            startActivity(intent)
        }
    }

    private fun deleteTugas(tugas: Tugas) {
        tugasViewModel.delete(tugas)
        Toast.makeText(this, "Tugas dihapus: ${tugas.namaTugas}", Toast.LENGTH_SHORT).show()
    }
}