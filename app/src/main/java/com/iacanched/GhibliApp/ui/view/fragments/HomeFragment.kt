package com.iacanched.GhibliApp.ui.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.iacanched.GhibliApp.R
import com.iacanched.GhibliApp.data.model.Film
import com.iacanched.GhibliApp.data.model.adapter.RecyclerViewAdapter
import com.iacanched.GhibliApp.databinding.FragmentMainBinding
import com.iacanched.GhibliApp.ui.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment: Fragment(){
    private lateinit var binding: FragmentMainBinding
    private val viewModel: AppViewModel by activityViewModels()

    private val adapter by lazy {
        activity?.let { RecyclerViewAdapter(context = it.applicationContext) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        requireActivity().window.statusBarColor = Color.BLACK
        setRecycler()
        return binding.root
    }

    private fun setRecycler() {
//        binding.countrySearch.setOnClickListener {
//            binding.countrySearch.setIconifiedByDefault(false)
//        }
        binding.countrySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter?.filter?.filter(query)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return false
            }
        }
        )
        viewModel.list.value?.let { adapter?.updateRecyclerView(it) }
        binding.moviesRecycler.adapter = adapter
        adapter?.onElementClick = this::nextStep

    }

    private fun nextStep(film: Film) {
        viewModel.setMovie(film)
        viewModel._selectedMovie.observe( viewLifecycleOwner) {
            NavHostFragment.findNavController(this).navigate(R.id.action_myHomeFragment_to_myMovieFragment)
        }
    }


}