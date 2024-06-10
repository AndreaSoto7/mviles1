package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practiacapipersonas.repositories.GeneroRepository
import com.example.practicaapipersonas.models.Genero

class GeneroViewModel : ViewModel() {
    private val _generoList: MutableLiveData<List<Genero>> by lazy {
        MutableLiveData<List<Genero>>()
    }
    val generoList: LiveData<List<Genero>> get() = _generoList

    fun fetchListaGeneros() {
        GeneroRepository.getGeneroList(
            success = { generos ->
                generos?.let {
                    _generoList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            })
    }
}