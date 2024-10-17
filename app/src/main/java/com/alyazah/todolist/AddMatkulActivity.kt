package com.alyazah.todolist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alyazah.todolist.databinding.AddMatkulBinding
import com.alyazah.todolist.entity.Matkul
import com.alyazah.todolist.viewmodel.MatkulViewModel

class AddMatkulActivity : AppCompatActivity() {

    private lateinit var binding: AddMatkulBinding
    private val matkulViewModel: MatkulViewModel by viewModels()
    private var userId: Int = -1 // Menyimpan ID user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddMatkulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getIntExtra("USER_ID", -1) // Ambil userId dari intent

        binding.btnSubmit.setOnClickListener {
            val namaMatkul = binding.edNama.text.toString().trim()
            val deskripsi = binding.edDesk.text.toString().trim()

            if (namaMatkul.isNotEmpty() && deskripsi.isNotEmpty()) {
                val matkul = Matkul(userId = userId, namaMatkul = namaMatkul, deskripsi = deskripsi)
                matkulViewModel.insert(matkul)
                Toast.makeText(this, "Matkul berhasil ditambahkan", Toast.LENGTH_SHORT).show()

                // Kembali ke MatkulActivity setelah menambahkan matkul
                finish()
            } else {
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
