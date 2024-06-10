package com.example.practicaapipersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapipersonas.databinding.LibroItemLayoutBinding
import com.example.practicaapipersonas.databinding.ProductoItemLayoutBinding
import com.example.practicaapipersonas.models.Libro

class LibroAdapter(var personaList: List<Libro>, val listener: OnProductoClickListener) :
    RecyclerView.Adapter<LibroAdapter.PersonaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding =
            ProductoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PersonaViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = personaList[position]
        holder.bind(persona, listener)
    }

    fun updateData(personaList: List<Libro>) {
        this.personaList = personaList
        notifyDataSetChanged()
    }

    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(producto: Libro, listener: OnProductoClickListener) {
            val binding = ProductoItemLayoutBinding.bind(itemView)
            binding.apply {
                lblLibroName2.text = producto.nombre
                lblLibroPrice.text = producto.ISBN.toString()
//                btnDeleteCategory.setOnClickListener {
//                    listener.onProductoDelete(producto)
//                }
//                lblCategoryName.setOnClickListener {
//                    listener.onProductoClick(producto)
//                }
//                root.setOnClickListener { listener.onProductoClick(producto) }
            }

        }
    }

    interface OnProductoClickListener {
        fun onProductoClick(producto: Libro)
        fun onProductoDelete(producto: Libro)
    }
}