package com.example.practicaapipersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapipersonas.databinding.LibroItemLayoutBinding
//import com.example.practicaapipersonas.databinding.GeneroItemLayoutBinding
import com.example.practicaapipersonas.models.Genero
import com.example.practicaapipersonas.models.Generos

class GeneroAdapter(val personaList: Generos, val listener: OnCategoriaClickListener) :
    RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val binding =
            LibroItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return GeneroViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        val genero = personaList[position]
        holder.bind(genero, listener)
    }

    fun updateData(generoList: Generos) {
        this.personaList.clear()
        this.personaList.addAll(generoList)
        notifyDataSetChanged()
    }

    class GeneroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(genero: Genero, listener: OnCategoriaClickListener) {
            val binding = LibroItemLayoutBinding.bind(itemView)
            binding.apply {
                lblLibroName.text = genero.nombre
                btnDeleteCategory.setOnClickListener {
                    listener.onGeneroDelete(genero)
                }
                lblLibroName.setOnClickListener {
                    listener.onGeneroClick(genero)
                }
                root.setOnClickListener { listener.onGeneroClick(genero) }
            }
        }
    }

    interface OnCategoriaClickListener {
        fun onGeneroClick(genero: Genero)
        fun onGeneroDelete(genero: Genero)
    }
}