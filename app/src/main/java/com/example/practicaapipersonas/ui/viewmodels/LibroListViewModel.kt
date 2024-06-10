package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.repositories.LibroRepository

class LibroListViewModel : ViewModel() {
    private val _libroList: MutableLiveData<List<Libro>> by lazy {
        MutableLiveData<List<Libro>>()
    }
    val libroList: LiveData<List<Libro>> get() = _libroList


    fun fetchListaLibros() {
        LibroRepository.getLibroList(
            success = { libros ->
                libros?.let {
                    _libroList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            })
    }
}