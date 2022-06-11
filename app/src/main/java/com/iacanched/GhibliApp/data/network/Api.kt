package com.iacanched.GhibliApp.data.network

import com.iacanched.GhibliApp.data.model.Film
import retrofit2.http.GET

interface Api {

    @GET("films/")
    suspend fun getAllMovies(): List<Film>

}