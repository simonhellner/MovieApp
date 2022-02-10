package com.simon.top10movies.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.simon.top10movies.utils.Constants.MOVIE_DB_API_BASE_URL

object RetrofitBuilder {
    private fun getRetrofitBuild(): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MOVIE_DB_API_BASE_URL)
            .build()
    }
    val movieDbApi: MovieDb = getRetrofitBuild().create(MovieDb::class.java)
}