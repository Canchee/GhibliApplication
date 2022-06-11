package com.iacanched.GhibliApp.data.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.iacanched.GhibliApp.data.model.Film
import com.iacanched.GhibliApp.databinding.MovieElementBinding
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(
    private val films: MutableList<Film> = mutableListOf(),
    private val context: Context,
    var onElementClick: (film: Film) -> Unit = {}
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), Filterable{
    private var filteredFilms: MutableList<Film> = films

    override fun getItemCount(): Int {
        return filteredFilms.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MovieElementBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(filteredFilms[position])
    }

    inner class ViewHolder(private val binding: MovieElementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        internal fun render(film: Film) {
            binding.titleMovieElement.text = film.title
            binding.yearMovieElement.text = film.release_date.toString()
            Picasso.get().load(film.image).into(binding.moviePictureElement)

            itemView.setOnClickListener {
                onElementClick(film)
            }
        }

    }
    fun updateRecyclerView(list: List<Film>) {
        this.films.clear()
        filteredFilms.clear()
        this.films.addAll(list)
        filteredFilms = films
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) filteredFilms = films else {
                    val filteredList = mutableListOf<Film>()
                    films
                        .filter {
                            (it.title.contains(constraint!!)) or
                                    (it.release_date.toString().contains(constraint))
                        }
                        .forEach { filteredList.add(it) }
                    filteredFilms = filteredList
                }
                return FilterResults().apply { values = filteredFilms }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredFilms = if (results?.values == null)
                    mutableListOf<Film>()
                else
                    results.values as MutableList<Film>
                notifyDataSetChanged()
            }

        }
    }

}