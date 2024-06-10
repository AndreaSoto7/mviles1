package com.example.practicaapipersonas.ui.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiacapipersonas.repositories.GeneroRepository
import com.example.practicaapipersonas.databinding.ActivityGeneroListBinding
import com.example.practicaapipersonas.models.Genero
import com.example.practicaapipersonas.ui.adapters.GeneroAdapter
import com.example.practicaapipersonas.ui.viewmodels.GeneroViewModel

class GeneroListActivity : AppCompatActivity(), GeneroAdapter.OnGeneroClickListener {
    private lateinit var binding: ActivityGeneroListBinding
    private lateinit var generoAdapter: GeneroAdapter
    private val generoViewModel: GeneroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneroListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize generoAdapter before calling setupRecyclerView
        generoAdapter = GeneroAdapter(mutableListOf(), this)

        setupRecyclerView()
        setupViewModel()
    }
    private fun setupRecyclerView() {
        binding.generoList.apply {
            layoutManager = LinearLayoutManager(this@GeneroListActivity)
            adapter = generoAdapter
        }
    }

    private fun setupViewModel() {
        generoViewModel.generoList.observe(this) { generos ->
            generoAdapter.updateData(generos.toMutableList())
        }
        generoViewModel.fetchListaGeneros()
    }

    override fun onGeneroClick(genero: Genero) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("generoId", genero.id)
        intent.putExtra("generoNombre", genero.nombre)
        startActivity(intent)
    }


   override fun onGeneroEdit(genero: Genero) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle("Editar Género")

    val input = EditText(this)
    input.setText(genero.nombre)
    builder.setView(input)

    builder.setPositiveButton("Guardar") { dialog, _ ->
        val newName = input.text.toString()
        if (newName.isBlank()) {
            Toast.makeText(this, "El nombre del género no puede estar vacío", Toast.LENGTH_SHORT).show()
        } else {
            val newGenero = Genero(newName)
            GeneroRepository.updateGenero(newGenero, genero.id!!,
                success = {
                    Toast.makeText(this, "Género actualizado con éxito", Toast.LENGTH_SHORT).show()
                    generoViewModel.fetchListaGeneros()
                },
                failure = { exception ->
                    Toast.makeText(this, "Error al actualizar el género: ${exception.message}", Toast.LENGTH_SHORT).show()
                })
        }
        dialog.dismiss()
    }

    builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }

    builder.show()
}

    override fun onGeneroDelete(genero: Genero) {
        GeneroRepository.deleteGenero(genero.id!!, {
            generoViewModel.fetchListaGeneros()
        }, {
            it.printStackTrace()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            generoViewModel.fetchListaGeneros()
        }
    }
}