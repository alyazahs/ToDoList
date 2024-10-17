package com.alyazah.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alyazah.todolist.R
import com.alyazah.todolist.entity.Tugas

class TugasAdapter(
    private val onEditClick: (Tugas) -> Unit,
    private val onDeleteClick: (Tugas) -> Unit
) : RecyclerView.Adapter<TugasAdapter.TugasViewHolder>() {

    private var tugasList = listOf<Tugas>()

    inner class TugasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.namaTugas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tugas, parent, false)
        return TugasViewHolder(view)
    }

    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        val tugas = tugasList[position]
        holder.nama.text = tugas.namaTugas

        holder.itemView.setOnClickListener {
            onEditClick(tugas)
        }

        holder.itemView.setOnClickListener {
            onDeleteClick(tugas)
        }
    }

    override fun getItemCount(): Int = tugasList.size

    fun setTugas(tugas: List<Tugas>) {
        this.tugasList = tugas
        notifyDataSetChanged()
    }
}
