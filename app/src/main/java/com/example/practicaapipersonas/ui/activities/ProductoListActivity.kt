package com.example.practicaapipersonas.ui.activities
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityProductoListBinding
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.ui.adapters.LibroAdapter
import com.example.practicaapipersonas.ui.viewmodels.LibroListViewModel

class ProductoListActivity : AppCompatActivity(), LibroAdapter.OnLibroClickListener {
    lateinit var binding: ActivityProductoListBinding
    private val model: LibroListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        setupViewModelObservers()
    }

    private fun setupRecyclerView() {
        binding.lstProductos.apply {
            layoutManager = LinearLayoutManager(this@ProductoListActivity)
            adapter = LibroAdapter(arrayListOf(), this@ProductoListActivity)
        }
    }

    private fun setupViewModelObservers() {
        model.libroList.observe(this) {
            binding.lstProductos.adapter = LibroAdapter(it, this)
        }
    }

    override fun onResume() {
        super.onResume()
        model.fetchListaLibros()
    }
    override fun onLibroClick(libro: Libro) {
        // Aquí va el código que quieres ejecutar cuando se hace clic en un libro
    }

    override fun onLibroDelete(libro: Libro) {
        // Aquí va el código que quieres ejecutar cuando se quiere eliminar un libro
    }
}