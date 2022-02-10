package com.simon.top10movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.simon.top10movies.utils.Constants.BACKDROP_BASE_URL
import com.simon.top10movies.data.model.Movie

class MovieSheetFragment: BottomSheetDialogFragment(){

    private lateinit var movie: Movie

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return inflater.inflate(R.layout.movie_sheet_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        Glide
            .with(requireView().getContext())
            .load(BACKDROP_BASE_URL + movie.backdrop_path)
            .into(requireView().findViewById(R.id.movieSheetBackdrop))

        requireView().findViewById<Button>(R.id.movieSheetCloseButton).setOnClickListener { dismiss() }

        requireView().findViewById<TextView>(R.id.movieSheetTitle).text = movie.title
        requireView().findViewById<RatingBar>(R.id.movieSheetRatingBar).progress = movie.vote_average.toInt()
        requireView().findViewById<TextView>(R.id.movieSheetPlaytime).text = movie.runtime.toString() + " ${getString(R.string.playtime_minutes)}"
        requireView().findViewById<TextView>(R.id.movieSheetOverview).text = movie.overview
        requireView().findViewById<TextView>(R.id.movieSheetGenres).text = movie.genres.joinToString(separator = ", ") { it.name }
    }

    fun setData(movie: Movie){
        this.movie = movie
    }
}