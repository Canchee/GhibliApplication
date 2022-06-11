package com.iacanched.GhibliApp.data

import com.iacanched.GhibliApp.data.model.Film
import com.iacanched.GhibliApp.data.network.Service
import javax.inject.Inject

class Repository @Inject constructor(private val service: Service) {

    suspend fun getAllMovies(): List<Film> {
        return service.getAllMovies()
    }

}