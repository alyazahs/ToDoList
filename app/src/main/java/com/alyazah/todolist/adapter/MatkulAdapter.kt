package com.alyazah.todolist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alyazah.todolist.R
import com.alyazah.todolist.TugasActivity
import com.alyazah.todolist.entity.Matkul

class MatkulAdapter(
    private val onEditClick: (Matkul) -> Unit,
    private val onDeleteClick: (Matkul) -> Unit
) : RecyclerView.Adapter<MatkulAdapter.MatkulViewHolder>() {

    private var matkul = listOf<Matkul>()

    inner class MatkulViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.nama)
        val deskripsi: TextView = itemView.findViewById(R.id.deskripsi)
        val btnEdit: Button = itemView.findViewById(R.id.btn_editMk) // Assuming you have edit button
        val btnDelete: Button = itemView.findViewById(R.id.btn_deleteMk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatkulViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_matkul, parent, false)
        return MatkulViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatkulViewHolder, position: Int) {
        val matkul = matkul[position]
        holder.nama.text = matkul.namaMatkul
        holder.deskripsi.text = matkul.deskripsi

        holder.itemView.setOnClickListener {
            // Mengarahkan ke TugasActivity saat matkul diklik
            val context = holder.itemView.context
            val intent = Intent(context, TugasActivity::class.java)
            intent.putExtra("MATKUL_ID", matkul.id) // Kirim ID matkul ke TugasActivity
            context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            onEditClick(matkul)
            true
        }

        holder.itemView.setOnLongClickListener {
            onDeleteClick(matkul)
            true
        }
    }

    override fun getItemCount(): Int = matkul.size

    fun setMatkul(matkul: List<Matkul>) {
        this.matkul = matkul
        notifyDataSetChanged()
    }
}
