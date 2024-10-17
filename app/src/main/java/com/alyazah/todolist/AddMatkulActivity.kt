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
    private var matkulId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddMatkulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getIntExtra("USER_ID", -1) // Ambil userId dari intent
        matkulId = intent.getIntExtra("MATKUL_ID", -1).takeIf { it != -1 }
        matkulId?.let {
            matkulViewModel.getMatkulById(it).observe(this) { matkul ->
                binding.edNama.setText(matkul.namaMatkul)
                binding.edDesk.setText(matkul.deskripsi)
            }
        }

        binding.btnSubmit.setOnClickListener {
            val namaMatkul = binding.edNama.text.toString().trim()
            val deskripsi = binding.edDesk.text.toString().trim()

            if (namaMatkul.isNotEmpty() && deskripsi.isNotEmpty()) {
                val matkul = Matkul(
                    id = matkulId ?: 0,
                    userId = userId,
                    namaMatkul = namaMatkul,
                    deskripsi = deskripsi
                )
                if (matkulId != null) {
                    matkulViewModel.update(matkul)
                    Toast.makeText(this, "Matkul berhasil diperbarui", Toast.LENGTH_SHORT).show()
                } else {
                    matkulViewModel.insert(matkul)
                    Toast.makeText(this, "Matkul berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}