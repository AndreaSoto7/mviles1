package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practiacapipersonas.repositories.GeneroRepository
import com.example.practicaapipersonas.models.Genero

class GeneroDetailViewModel : ViewModel() {
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: LiveData<Boolean> get() = _closeActivity
    private val _genero: MutableLiveData<Genero?> by lazy {
        MutableLiveData<Genero?>(null)
    }
    val genero: LiveData<Genero?> get() = _genero

    fun saveGenero(nombre: String, id: Int) {
        val genero = Genero(
            nombre = nombre
        )
        if (id != 0) {
            genero.id = id
            GeneroRepository.updateGenero(genero,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        } else {
            GeneroRepository.insertGenero(genero,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
    }

    fun loadGenero(id: Int) {
        GeneroRepository.getGenero(id,
            success = {
                _genero.value = it
            },
            failure = {
                it.printStackTrace()
            })
    }
}