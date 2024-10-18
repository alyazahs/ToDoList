package com.alyazah.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alyazah.todolist.R
import com.alyazah.todolist.entity.User
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.alyazah.todolist.MatkulActivity
import com.google.android.material.textfield.TextInputEditText

class UserAdapter(
    private val onEditClick: (User) -> Unit,
    private val onDeleteClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users = listOf<User>()

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.namaUsr)
        val btnEdit: Button = itemView.findViewById(R.id.btn_edit)
        val btnDelete: Button = itemView.findViewById(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.nama.text = user.username

        holder.itemView.setOnClickListener {
            // Tampilkan popup login
            showLoginDialog(holder.itemView.context, user)
        }

        holder.btnEdit.setOnClickListener {
            onEditClick(user)
        }

        holder.btnDelete.setOnClickListener {
            onDeleteClick(user)
        }
    }

    override fun getItemCount(): Int = users.size

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    private fun showLoginDialog(context: Context, user: User) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.login, null)
        val usernameInput = dialogView.findViewById<TextInputEditText>(R.id.username_input)
        val passwordInput = dialogView.findViewById<TextInputEditText>(R.id.password_input)
        val btnLogin = dialogView.findViewById<Button>(R.id.btn_login)

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false) // Dialog tidak bisa ditutup tanpa aksi
            .create()

        dialog.show()

        btnLogin.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            // Verifikasi username dan password
            if (username == user.username && password == user.password) {
                // Login berhasil, arahkan ke MatkulActivity
                val intent = Intent(context, MatkulActivity::class.java).apply {
                    putExtra("USER_ID", user.id) // Kirim userId ke MatkulActivity
                }
                context.startActivity(intent)
                dialog.dismiss() // Tutup dialog setelah login berhasil
            } else {
                // Login gagal, tampilkan pesan error
                Toast.makeText(
                    context,
                    "Login gagal. Cek username atau password.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}