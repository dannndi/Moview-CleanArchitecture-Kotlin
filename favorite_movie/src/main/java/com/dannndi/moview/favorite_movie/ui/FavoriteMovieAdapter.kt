package com.dannndi.moview.favorite_movie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.databinding.ItemNowPlayingMovieBinding

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {
    private val movies = mutableListOf<Movie>()
    private lateinit var onClick: (Int) -> Unit

    fun setList(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnClick(click: (Int) -> Unit) {
        onClick = click
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNowPlayingMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(private val binding: ItemNowPlayingMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w154${movie.backdropPath}")
                    .into(imgBackdrop)
                tvTitle.text = movie.title
                tvRating.text = movie.rating.toString()
                tvYear.text = movie.releaseDate.split("-")[0]
            }
        }
    }
}