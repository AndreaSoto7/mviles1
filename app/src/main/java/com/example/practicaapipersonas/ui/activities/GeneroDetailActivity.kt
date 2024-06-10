package com.example.practicaapipersonas.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityGeneroDetailBinding
import com.example.practicaapipersonas.ui.viewmodels.GeneroDetailViewModel

class GeneroDetailActivity : AppCompatActivity() {
    private var id: Int = 0
    lateinit var binding: ActivityGeneroDetailBinding
    private val model: GeneroDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()

        id = intent.getIntExtra("generoId", -1)
        if (id != -1) {
            model.loadGenero(id)
        }
    }

    private fun setupViewModelObservers() {
        model.closeActivity.observe(this) {
            if (it) {
                finish()
            }
        }
        model.genero.observe(this) {
            if (it == null) {
                return@observe
            }
            binding.txtGeneroName.editText?.setText(it.nombre)
        }
    }

    private fun setupEventListeners() {
        binding.btnSaveGenero.setOnClickListener {
            model.saveGenero(binding.txtGeneroName.editText?.text.toString(), id)
        }
    }
}