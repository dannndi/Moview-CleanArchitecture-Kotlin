package com.dannndi.moview.favorite_movie.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dannndi.moview.favorite_movie.di.favoriteModule
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.favorite_movie.databinding.ActivityFavoriteBinding
import com.dannndi.moview.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()

    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter
    private var listFavoriteMovie = emptyList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        loadKoinModules(favoriteModule)

        viewModel.getFavoriteMovie()

        favoriteMovieAdapter = FavoriteMovieAdapter()
        favoriteMovieAdapter.setOnClick { position ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra("Movie", listFavoriteMovie[position])
                startActivity(it)
            }
        }
        binding.rvNowPlayingMovie.apply {
            adapter = favoriteMovieAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }

        viewModel.listFavoriteModule.observe(this) { list ->
            listFavoriteMovie = list
            favoriteMovieAdapter.setList(listFavoriteMovie)
        }
    }
}