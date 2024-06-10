package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practiacapipersonas.repositories.LibroRepository
import com.example.practicaapipersonas.models.Libros

class LibroListViewModel : ViewModel() {
    private val _libroList: MutableLiveData<Libros> by lazy {
        MutableLiveData<Libros>(Libros())
    }
    val libroList: LiveData<Libros> get() = _libroList

    fun fetchListaLibros() {
        LibroRepository.getLibroList(
            success = { libros ->
                libros?.let {
                    _libroList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }
}