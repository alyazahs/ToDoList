package com.alyazah.todolist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alyazah.todolist.databinding.AddUserBinding
import com.alyazah.todolist.entity.User
import com.alyazah.todolist.viewmodel.UserViewModel

class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: AddUserBinding
    private val userViewModel: UserViewModel by viewModels()

    // Menyimpan ID user saat melakukan edit
    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menerima data dari intent jika ada (untuk edit)
        userId = intent.getIntExtra("USER_ID", -1).takeIf { it != -1 }

        if (userId != null) {
            // Mengisi field dengan data user yang ada
            val nama = intent.getStringExtra("USER_NAME") ?: ""
            val password = intent.getStringExtra("USER_PASSWORD") ?: ""
            binding.edUser.setText(nama)
            binding.edPass.setText(password)
        }

        binding.btnSubmit.setOnClickListener {
            val nama = binding.edUser.text.toString().trim()
            val password = binding.edPass.text.toString().trim()

            if (nama.isNotEmpty() && password.isNotEmpty()) {
                if (userId == null) {
                    // Tambah user baru
                    val user = User(username = nama, password = password)
                    userViewModel.insert(user)
                    Toast.makeText(this, "User berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                } else {
                    // Update user yang ada
                    val updatedUser = User(id = userId!!, username = nama, password = password)
                    userViewModel.update(updatedUser)
                    Toast.makeText(this, "User berhasil diperbarui", Toast.LENGTH_SHORT).show()
                }
                finish() // Kembali ke Activity sebelumnya
            } else {
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
