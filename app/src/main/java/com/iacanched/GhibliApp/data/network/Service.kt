package com.iacanched.GhibliApp.data.network

import com.iacanched.GhibliApp.data.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Service @Inject constructor(private val api: Api) {

    suspend fun getAllMovies(): List<Film> {
        return withContext(Dispatchers.IO) {
            api.getAllMovies()
        }
    }


}