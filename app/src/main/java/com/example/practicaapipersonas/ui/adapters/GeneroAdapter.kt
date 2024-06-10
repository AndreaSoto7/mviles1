package com.example.practicaapipersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.GeneroItemLayoutBinding
import com.example.practicaapipersonas.models.Genero
import com.google.android.material.button.MaterialButton


class GeneroAdapter(val generoList: MutableList<Genero>, val listener: OnGeneroClickListener) :
    RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {

    class GeneroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val generoName: TextView = itemView.findViewById(R.id.generoName)
        val btnEditarGenero: MaterialButton = itemView.findViewById(R.id.btnEditarGeneroItem)
        val btnDeleteGenero: MaterialButton = itemView.findViewById(R.id.btnDeleteGenero)

        fun bind(genero: Genero, listener: OnGeneroClickListener) {
            generoName.text = genero.nombre
            btnEditarGenero.setOnClickListener { listener.onGeneroClick(genero) }
            btnDeleteGenero.setOnClickListener { listener.onGeneroDelete(genero) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genero_item_layout, parent, false)
        return GeneroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return generoList.size
    }

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        val genero = generoList[position]
        holder.bind(genero, listener)
    }

    fun updateData(generoList: List<Genero>) { // Cambia Generos a List<Genero>
        this.generoList.clear()
        this.generoList.addAll(generoList)
        notifyDataSetChanged()
    }



    interface OnGeneroClickListener {
    fun onGeneroClick(genero: Genero)
    fun onGeneroEdit(genero: Genero)
    fun onGeneroDelete(genero: Genero)
}
}