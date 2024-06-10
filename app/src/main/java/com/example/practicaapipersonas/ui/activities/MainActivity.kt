package com.example.practicaapipersonas.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityMainBinding
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.models.Libros
import com.example.practicaapipersonas.repositories.LibroRepository
import com.example.practicaapipersonas.ui.adapters.LibroAdapter
import com.example.practicaapipersonas.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), LibroAdapter.OnLibroClickListener {
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    private var generoId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        generoId = intent.getIntExtra("generoId", -1)
        val generoNombre = intent.getStringExtra("generoNombre") ?: ""
        if (generoId != -1) {
            title = generoNombre
            Log.d("MainActivity", "onCreate vista")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        setupEventListeners()
        setupRecyclerView()
        setupViewModelListeners()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_go_next_page) {
            //val intent = Intent(this, GeneroDetailActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.action_libros) {
            // val intent = Intent(this, LibroDetailActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        model.fetchListaLibros()
        Log.d("MainActivity", "onResume obtener la lista de libros")
    }

    private fun setupEventListeners() {
        binding.fabAddLibro.setOnClickListener {
            // val intent = Intent(this, GeneroDetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewModelListeners() {
        model.libroList.observe(this) {
            val adapter = (binding.lstPersonas.adapter as LibroAdapter)
            adapter.updateData(it)
        }
    }

    //LibroAdapter puente que ayuda mostrar los libros en la vista
    private fun setupRecyclerView() {
        Log.d("MainActivity", "setupRecyclerView iniciado")
        binding.lstPersonas.apply {
            this.adapter = LibroAdapter(Libros(), this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        Log.d("MainActivity", "setupRecyclerView completado")
    }


    override fun onLibroDelete(libro: Libro) {
        LibroRepository.deleteLibro(libro.id!!,
            success = {
                model.fetchListaLibros()
            },
            failure = {
                it.printStackTrace()
            })
    }

    override fun onLibroClick(libro: Libro) {
        val intent = Intent(this, LibroDetailActivity::class.java)
        intent.putExtra("libro", libro)
        startActivity(intent)
    }

}