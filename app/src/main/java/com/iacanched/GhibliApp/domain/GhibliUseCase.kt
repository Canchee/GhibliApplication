package com.iacanched.GhibliApp.domain

import com.iacanched.GhibliApp.data.Repository
import com.iacanched.GhibliApp.data.model.Film
import javax.inject.Inject

class GhibliUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): List<Film> = repository.getAllMovies()

}