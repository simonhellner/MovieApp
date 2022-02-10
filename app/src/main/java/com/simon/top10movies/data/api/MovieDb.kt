package com.simon.top10movies.data.api

import com.simon.top10movies.utils.Constants.MOVIE_DB_API_KEY
import com.simon.top10movies.data.model.Movie
import com.simon.top10movies.data.model.ResultPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDb {

    @GET("movie/popular" )
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = MOVIE_DB_API_KEY,
    ): Response<ResultPage<Movie>>

    @GET("movie/{movieId}" )
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = MOVIE_DB_API_KEY,
    ): Response<Movie>

}