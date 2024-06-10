package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practiacapipersonas.repositories.GeneroRepository
import com.example.practicaapipersonas.models.Libro
import android.util.Log
import com.example.practicaapipersonas.repositories.LibroRepository

class MainViewModel : ViewModel() {
    private val _libroList: MutableLiveData<List<Libro>> by lazy {
        MutableLiveData<List<Libro>>(emptyList())
    }
    val libroList: LiveData<List<Libro>> get() = _libroList

    fun fetchListaLibros() {
        Log.d("MainViewModel", "fetchListaLibros() llamado")
        LibroRepository.getLibroList(
            success = { libros ->
                libros?.let {
                    _libroList.value = it
                    Log.d("MainViewModel", "Libros recibidos: $it")
                }
            },
            failure = {
                it.printStackTrace()
                Log.e("MainViewModel", "Error al obtener los libros", it)
            }
        )
    }
}