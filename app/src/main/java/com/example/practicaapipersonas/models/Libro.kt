package com.example.practicaapipersonas.models

import java.io.Serializable

typealias Libros = ArrayList<Libro>

data class Libro(
    var nombre: String,
    var autor: String,
    var editorial: String,
    var imagen: String,
    var sinopsis: String,
    var isbn: String,
    var calificacion: Float,
    var generos: List<Genero>
):Serializable {
    val id: Int? = null
}