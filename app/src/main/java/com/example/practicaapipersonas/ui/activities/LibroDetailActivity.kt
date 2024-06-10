package com.example.practicaapipersonas.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practicaapipersonas.databinding.ActivityLibroDetailBinding
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.repositories.LibroRepository

class LibroDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityLibroDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val libro = intent.getSerializableExtra("libro") as Libro
        libro?.let {
            // Set views with libro details
            binding.lblSinopsisLibro.text = it.sinopsis
            binding.lblCalificacionLibro.text = it.calificacion.toString()
            binding.lblIsbnLibro.text = it.isbn
            binding.lblGenerosLibroDetail.text = it.generos.joinToString(", ")
            binding.btnEliminarLibro.setOnClickListener {
                // Delete libro
                libro.id?.let { it1 ->
                    LibroRepository.deleteLibro(it1,
                        success = {
                            // Libro eliminado con éxito
                            Log.d("LibroDetailActivity", "Libro eliminado con éxito")
                        },
                        failure = { error ->
                            // Error al eliminar el libro
                            Log.e("LibroDetailActivity", "Error al eliminar el libro", error)
                        }
                    )
                }
            }
            // Load image using Glide or other image loading library
            Glide.with(this).load(it.imagen).into(binding.imgLibroDetail)
        }
    }
}