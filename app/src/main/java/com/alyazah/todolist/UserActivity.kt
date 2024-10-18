package com.alyazah.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alyazah.todolist.adapter.UserAdapter
import com.alyazah.todolist.databinding.ActivityUserBinding
import com.alyazah.todolist.entity.User
import com.alyazah.todolist.viewmodel.UserViewModel

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(
            onEditClick = { user -> editUser(user) },
            onDeleteClick = { user -> deleteUser(user) }
        )

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapter

        userViewModel.allUsers.observe(this) { users ->
            users?.let {
                adapter.setUsers(it)
            }
        }

        binding.btnUser.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun editUser(user: User) {
        val intent = Intent(this, AddUserActivity::class.java).apply {
            putExtra("USER_ID", user.id)
            putExtra("USER_NAME", user.username)
            putExtra("USER_PASSWORD", user.password)
        }
        startActivity(intent)
    }

    private fun deleteUser(user: User) {
        userViewModel.delete(user)
        Toast.makeText(this, "User dihapus: ${user.username}", Toast.LENGTH_SHORT).show()
    }
}
