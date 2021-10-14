package com.androiddemo.ui.movies

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androiddemo.R
import com.androiddemo.model.Movies
import com.androiddemo.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.item_movies.view.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var data : Movies

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        title = resources.getString(R.string.movie_details)

        val extras = intent.extras
        data = intent.extras?.get(Constants.DATA) as Movies

        tvTitle.text = data.title
        year.text = data.year
        director.text = resources.getString(R.string.director) + " : " + data.director
        description.text = data.description

        Picasso.get().load(data.images).into(image)

    }
}