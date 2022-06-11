package com.iacanched.GhibliApp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iacanched.GhibliApp.data.model.Film
import com.iacanched.GhibliApp.domain.GhibliUseCase
import kotlinx.coroutines.launch

class AppViewModel @ViewModelInject constructor(private val useCase: GhibliUseCase) :
    ViewModel(){
    var _moviesList = MutableLiveData<List<Film>>()

    val list: LiveData<List<Film>>
        get() = _moviesList

    var _selectedMovie = MutableLiveData<Film>()

    val selectedFilm: LiveData<Film>
        get() = _selectedMovie


    fun getMovies() {
        viewModelScope.launch {
            _moviesList.value = useCase.invoke()
        }
    }

    fun setMovie(film: Film) {
        _selectedMovie.value = film
    }

}