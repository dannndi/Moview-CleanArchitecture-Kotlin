package com.dannndi.moview.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    private lateinit var movie: Movie
    private lateinit var castAdapter: CastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        movie = intent.getParcelableExtra<Movie>("Movie") as Movie

        viewModel.getMovieDetail(movie.id)
        viewModel.getMovieCast(movie.id)
        viewModel.checkIsFavorite(movie.id)
        showLoading(true)

        castAdapter = CastAdapter()
        binding.rvCast.apply {
            adapter = castAdapter
            layoutManager = LinearLayoutManager(
                this@DetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }

        viewModel.movieDetail.observe(this) { movie ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
                .into(binding.ivBackdrop)
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w154${movie.posterPath}")
                .into(binding.ivPoster)
            binding.tvTitle.text = movie.originalTitle
            binding.tvYear.text = movie.releaseDate.split("-")[0]
            binding.tvDuration.text = getFormattedTime(movie.runtime)
            binding.tvRating.text = movie.voteAverage.toString()
            binding.tvLanguange.text = movie.spokenLanguages[0].name
            binding.tvGenres.text = movie.genres.joinToString(",") { it.name }
            binding.tvOverview.text = movie.overview
            showLoading(false)
        }

        viewModel.listCast.observe(this) { castList ->
            castAdapter.setList(castList)
        }

        viewModel.isFavorite.observe(this) { state ->
            binding.btnFavorite.isChecked = state
        }

        binding.btnFavorite.setOnClickListener {
            if (binding.btnFavorite.isChecked) {
                viewModel.addToFavoriteMovie(movie)
            } else {
                viewModel.deleteFromFavoriteMovie(movie)
            }
        }
    }

    private fun getFormattedTime(time: Int): String {
        val hour = time / 60
        val minute = time % 60
        return "${hour}h ${minute}m"
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.apply {
                loading.visibility = View.VISIBLE
                btnFavorite.visibility = View.GONE
                ivBackdrop.visibility = View.GONE
                ivPoster.visibility = View.GONE
                tvTitle.visibility = View.GONE
                tvYear.visibility = View.GONE
                tvDuration.visibility = View.GONE
                tvRating.visibility = View.GONE
                tvLanguange.visibility = View.GONE
                cast.visibility = View.GONE
                rvCast.visibility = View.GONE
                genres.visibility = View.GONE
                tvGenres.visibility = View.GONE
                overview.visibility = View.GONE
                tvOverview.visibility = View.GONE
            }
        } else {
            binding.apply {
                loading.visibility = View.GONE
                btnFavorite.visibility = View.VISIBLE
                ivBackdrop.visibility = View.VISIBLE
                ivPoster.visibility = View.VISIBLE
                tvTitle.visibility = View.VISIBLE
                tvYear.visibility = View.VISIBLE
                tvDuration.visibility = View.VISIBLE
                tvRating.visibility = View.VISIBLE
                tvLanguange.visibility = View.VISIBLE
                cast.visibility = View.VISIBLE
                rvCast.visibility = View.VISIBLE
                genres.visibility = View.VISIBLE
                tvGenres.visibility = View.VISIBLE
                overview.visibility = View.VISIBLE
                tvOverview.visibility = View.VISIBLE
            }
        }
    }
}