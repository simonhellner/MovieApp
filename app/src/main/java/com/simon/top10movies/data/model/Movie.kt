package com.simon.top10movies.data.model

data class Movie(
    val backdrop_path: String,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val title: String,

    val vote_average: Double,
    val genres: List<Genre>,
    val runtime: Int,
)