package com.iacanched.GhibliApp.ui.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iacanched.GhibliApp.databinding.FragmentMovieBinding
import com.iacanched.GhibliApp.ui.viewmodel.AppViewModel
import com.iacanched.GhibliApp.utils.toHoursAndMinutes
import com.iacanched.GhibliApp.utils.toRatingStars
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment: Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel: AppViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        requireActivity().window.statusBarColor = Color.WHITE
        updateUI()
        return binding.root
    }

    private fun updateUI() {
        viewModel.selectedFilm.value?.let {
            Picasso.get().load(it.movie_banner).into(binding.movieBanner)
            Picasso.get().load(it.image).into(binding.movieImage)
            binding.movieTitle.text = it.title
            binding.movieOriginalTitle.text = it.original_title_romanised
            binding.movieYear.text = it.release_date.toString()
            binding.movieDuration.text = it.running_time.toHoursAndMinutes
            binding.movieRatingBar.rating = it.rt_score.toRatingStars
            binding.description.text = it.description
            binding.movieDirector.text = it.director
            binding.movieProducer.text = it.producer
        }
    }


}