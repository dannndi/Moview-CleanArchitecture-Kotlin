package com.dannndi.moview.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.dannndi.moview.core.domain.model.Movie
import com.dannndi.moview.databinding.ActivityHomeBinding
import com.dannndi.moview.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    private lateinit var popularAdapter: PopularAdapter
    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private var popularMovie = emptyList<Movie>()
    private var nowPlayingMovie = emptyList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFavorite.setOnClickListener {
            val uri = Uri.parse("moview://favorite_movie")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        val transformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.90f + r * 0.05f
            }
        }
        popularAdapter = PopularAdapter()
        popularAdapter.setOnClick { position ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra("Movie", popularMovie[position])
                startActivity(it)
            }
        }

        binding.rvPopularMovie.apply {
            adapter = popularAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 2
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(transformer)
        }

        nowPlayingAdapter = NowPlayingAdapter()
        nowPlayingAdapter.setOnClick { position ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra("Movie", nowPlayingMovie[position])
                startActivity(it)
            }
        }

        binding.rvNowPlayingMovie.apply {
            adapter = nowPlayingAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setHasFixedSize(true)
        }

        viewModel.popularMovie.observe(this) { movies ->
            popularMovie = movies
            popularAdapter.setList(popularMovie)
        }

        viewModel.nowPlayingMovies.observe(this) { movies ->
            nowPlayingMovie = movies
            nowPlayingAdapter.setList(nowPlayingMovie)
        }

    }
}
