package com.androiddemo.ui.movies

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddemo.R
import com.androiddemo.adapter.MoviesAdapter
import com.androiddemo.model.Movies
import com.androiddemo.utils.Utils

class MoviesActivity : AppCompatActivity() {

    private var context: Context = this
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var arrayListMovies = ArrayList<Movies>()
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var rvMovies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        title = resources.getString(R.string.movies)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        arrayListMovies = Utils.getMovies()
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies = findViewById(R.id.rvMovies)
        rvMovies.layoutManager = linearLayoutManager
        rvMovies.itemAnimator = DefaultItemAnimator()
        moviesAdapter = MoviesAdapter(context, arrayListMovies)
        rvMovies.adapter = moviesAdapter
    }
}