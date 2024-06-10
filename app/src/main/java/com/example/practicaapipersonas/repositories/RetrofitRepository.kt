package com.example.practicaapipersonas.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRepository {
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://apilibreria.jmacboy.com/api/libros/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}