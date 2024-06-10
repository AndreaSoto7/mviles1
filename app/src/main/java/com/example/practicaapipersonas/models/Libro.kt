package com.example.practicaapipersonas.models

typealias Libros = ArrayList<Libro>

data class Libro(
    val id: Int,
    val nombre: String,
    val ISBN: String,
    val urlImagen: String,
    val sinopsis: String,
    val calificacion: Float
) {
    var createdAt: String? = null
    var updatedAt: String? = null
    var generos: List<Genero> = emptyList()
}