package com.alyazah.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alyazah.todolist.adapter.MatkulAdapter
import com.alyazah.todolist.databinding.ActivityMatkulBinding
import com.alyazah.todolist.entity.Matkul
import com.alyazah.todolist.viewmodel.MatkulViewModel

class MatkulActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatkulBinding
    private val matkulViewModel: MatkulViewModel by viewModels()
    private lateinit var adapter: MatkulAdapter
    private var userId: Int = -1 // Menyimpan ID user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatkulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getIntExtra("USER_ID", -1) // Ambil userId dari intent

        adapter = MatkulAdapter(
            onEditClick = { matkul -> editMatkul(matkul) },
            onDeleteClick = { matkul -> deleteMatkul(matkul) }
        )

        binding.rvMatkul.layoutManager = LinearLayoutManager(this)
        binding.rvMatkul.adapter = adapter

        // Mengamati mata kuliah berdasarkan userId
        matkulViewModel.getMatkulByUserId(userId).observe(this) { matkuls ->
            matkuls?.let {
                adapter.setMatkul(it) // Memperbarui adapter dengan data terbaru
            }
        }

        binding.btnMatkul.setOnClickListener {
            val intent = Intent(this, AddMatkulActivity::class.java)
            intent.putExtra("USER_ID", userId) // Kirim userId ke AddMatkulActivity
            startActivity(intent)
        }
    }

    private fun editMatkul(matkul: Matkul) {
        // Logika untuk mengedit matkul
        Toast.makeText(this, "Edit Matkul: ${matkul.namaMatkul}", Toast.LENGTH_SHORT).show()
    }

    private fun deleteMatkul(matkul: Matkul) {
        matkulViewModel.delete(matkul)
        Toast.makeText(this, "Matkul dihapus: ${matkul.namaMatkul}", Toast.LENGTH_SHORT).show()
    }
}
