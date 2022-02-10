package com.simon.top10movies.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simon.top10movies.data.repositoryy.MovieDbRepo
import com.simon.top10movies.data.model.Movie
import com.simon.top10movies.data.model.ResultPage
import com.simon.top10movies.utils.Resource
import kotlinx.coroutines.launch
import kotlin.Exception

class MovieViewModel(private val repo: MovieDbRepo): ViewModel() {
    val popularMoviesResponse: MutableLiveData<Resource<ResultPage<Movie>>> = MutableLiveData()
    val movieDetailsResponse: MutableLiveData<Resource<Movie>> = MutableLiveData()

    fun getPopularMovies(){
        viewModelScope.launch{
            try{
                val response = repo.getPopularMovies()
                if(response.isSuccessful){
                    popularMoviesResponse.value = Resource.success(response.body()!!)
                }
                else{
                    popularMoviesResponse.value = Resource.error(response.code())
                }
            }
            catch (e: Exception){
                popularMoviesResponse.value = Resource.error(0, message = e.message!!)
            }
        }
    }

    fun getMovieDetails(movieId: Int){
        viewModelScope.launch{
            try{
                val response = repo.getMovieDetails(movieId)
                if(response.isSuccessful){
                    movieDetailsResponse.value = Resource.success(response.body()!!)
                }
                else{
                    movieDetailsResponse.value = Resource.error(response.code())
                }
            }
            catch (e: Exception){
                movieDetailsResponse.value = Resource.error(0, message = e.message!!)
            }
        }
    }
}