package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import com.example.practiacapipersonas.repositories.GeneroRepository
import com.example.practiacapipersonas.repositories.LibroRepository
import com.example.practicaapipersonas.models.Generos
import com.example.practicaapipersonas.models.Libros
import android.util.Log

class MainViewModel : ViewModel() {
    private val _categoryList: MutableLiveData<Libros> by lazy {
    MutableLiveData<Libros>(Libros())
}
val categoryList: LiveData<Libros> get() = _categoryList

    fun fetchListaLibros() {
        Log.d("MainViewModel", "fetchListaLibros() llamado")
        LibroRepository.getLibroList(
            success = { libros ->
                libros?.let {
                    it.sortByDescending { libro -> libro.calificacion } // Ordena los libros por calificación
                    _categoryList.value = it
                    Log.d("MainViewModel", "Libros recibidos: $it")
                } ?: run {
                    Log.d("MainViewModel", "La lista de libros está vacía o es nula")
                }
            },
            failure = {
                it.printStackTrace()
                Log.e("MainViewModel", "Error al obtener los libros", it)
            }
        )
    }
}
