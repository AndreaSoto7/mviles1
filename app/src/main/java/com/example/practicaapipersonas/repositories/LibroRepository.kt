package com.example.practicaapipersonas.repositories

import android.util.Log
import com.example.practicaapipersonas.api.APILibreriaService
import com.example.practicaapipersonas.models.Libro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LibroRepository {
    private val api: APILibreriaService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(APILibreriaService::class.java)
    }

    fun getLibroById(id: Int, success: (Libro) -> Unit, failure: (Throwable) -> Unit) {
        api.getLibroById(id).enqueue(object : Callback<Libro> {
            override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                if (response.isSuccessful) {
                    response.body()?.let { success(it) }
                } else {
                    failure(Throwable("Error ${response.code()} occurred"))
                }
            }

            override fun onFailure(call: Call<Libro>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun insertLibro(libro: Libro, success: (Libro) -> Unit, failure: (Throwable) -> Unit) {
        val libroData = Libro(
            nombre = libro.nombre,
            autor = libro.autor,
            editorial = libro.editorial,
            imagen = libro.imagen,
            sinopsis = libro.sinopsis,
            isbn = libro.isbn,
            calificacion = libro.calificacion,
            generos = listOf()
        )
        api.insertLibro(libroData).enqueue(object : Callback<Libro> {
            override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                if (response.isSuccessful) {
                    response.body()?.let { success(it) }
                } else {
                    failure(Throwable("Error ${response.code()} occurred"))
                }
            }

            override fun onFailure(call: Call<Libro>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun updateLibro(libro: Libro, id: Int, success: (Libro) -> Unit, failure: (Throwable) -> Unit) {
        val libro = Libro(
            nombre = libro.nombre,
            autor = libro.autor,
            editorial = libro.editorial,
            imagen = libro.imagen,
            sinopsis = libro.sinopsis,
            isbn = libro.isbn,
            calificacion = libro.calificacion,
            generos = listOf()
        )
        api.updateLibro(libro, id).enqueue(object : Callback<Libro> {
            override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                if (response.isSuccessful) {
                    response.body()?.let { success(it) }
                } else {
                    failure(Throwable("Error ${response.code()} occurred"))
                }
            }

            override fun onFailure(call: Call<Libro>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun deleteLibro(id: Int, success: () -> Unit, failure: (Throwable) -> Unit) {
        api.deleteLibro(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    success()
                } else {
                    failure(Throwable("Error ${response.code()} occurred"))
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun getLibroList(success: (List<Libro>) -> Unit, failure: (Throwable) -> Unit) {
        api.getLibros().enqueue(object : Callback<List<Libro>> {
            override fun onResponse(call: Call<List<Libro>>, response: Response<List<Libro>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("LibroRepository", "Libros cargados correctamente: $it")
                        success(it)
                    }
                } else {
                    Log.e(
                        "LibroRepository",
                        "Error ${response.code()} occurred while loading libros"
                    )
                    failure(Throwable("Error ${response.code()} occurred"))
                }
            }

            override fun onFailure(call: Call<List<Libro>>, t: Throwable) {
                Log.e("LibroRepository", "Error occurred while loading libros", t)
                failure(t)
            }
        })
    }
}