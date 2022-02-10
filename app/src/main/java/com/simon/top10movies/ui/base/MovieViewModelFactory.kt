package com.simon.top10movies.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simon.top10movies.data.repositoryy.MovieDbRepo
import com.simon.top10movies.ui.viewmodel.MovieViewModel

class MovieViewModelFactory(private val repo: MovieDbRepo): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(repo) as T
    }
}