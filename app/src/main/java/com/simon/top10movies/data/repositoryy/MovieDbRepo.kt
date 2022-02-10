package com.simon.top10movies.data.repositoryy

import com.simon.top10movies.data.api.RetrofitBuilder
import com.simon.top10movies.data.model.Movie
import com.simon.top10movies.data.model.ResultPage
import retrofit2.Response

class MovieDbRepo {
    suspend fun getPopularMovies() : Response<ResultPage<Movie>> {
        return RetrofitBuilder.movieDbApi.getPopularMovies()
    }

    suspend fun getMovieDetails(movieId: Int) : Response<Movie>{
        return RetrofitBuilder.movieDbApi.getMovieDetails(movieId)
    }
}