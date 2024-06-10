package com.example.practicaapipersonas.api

import com.example.practicaapipersonas.models.Genero
import com.example.practicaapipersonas.models.Generos
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.models.Libros
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APILibreriaService {
    @GET("libros")
    fun getLibros(): Call<Libros>

    @GET("libros/{id}")
    fun getLibroById(
        @Path("id") id: Int
    ): Call<Libro?>

    @POST("libros")
    fun insertLibro(
        @Body libro: Libro
    ): Call<Libro>

    @PUT("libros/{id}")
    fun updateLibro(
        @Body libro: Libro,
        @Path("id") id: Int
    ): Call<Libro>

    @DELETE("libros/{id}")
    fun deleteLibro(
        @Path("id") id: Int
    ): Call<Void>


    @GET("generos")
    fun getGeneros(): Call<Generos>

    @GET("generos/{id}")
    fun getGeneroById(
        @Path("id") id: Int
    ): Call<Genero?>

    @POST("generos")
    fun insertGenero(
        @Body genero: Genero
    ): Call<Genero>

    @PUT("generos/{id}")
    fun updateGenero(
        @Body genero: Genero,
        @Path("id") id: Int
    ): Call<Genero>

    @DELETE("generos/{id}")
    fun deleteGenero(
        @Path("id") id: Int
    ): Call<Void>

}