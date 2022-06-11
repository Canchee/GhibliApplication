package com.iacanched.GhibliApp.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.iacanched.GhibliApp.R
import com.iacanched.GhibliApp.databinding.FragmentSplashBinding
import com.iacanched.GhibliApp.ui.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment: Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: AppViewModel by activityViewModels()
//    private val adapter by lazy {
//        RecyclerViewAdapter(context = this)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observer()
        downloadMovies()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun downloadMovies() {
        viewModel.getMovies()
    }

    private fun observer() {
        viewModel._moviesList.observe( viewLifecycleOwner) {
            binding.loadingMessageText.visibility = View.INVISIBLE
            binding.loadingLottieAnimation.pauseAnimation()
            binding.loadingLottieAnimation.progress = 1f
            NavHostFragment.findNavController(this).navigate(R.id.action_mySplashFragment_to_myHomeFragment)

        }
    }

}