package com.simon.top10movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simon.top10movies.data.repository.MovieDbRepo
import com.simon.top10movies.ui.adapter.MovieAdapter
import com.simon.top10movies.ui.viewmodel.MovieViewModel
import com.simon.top10movies.ui.base.MovieViewModelFactory
import android.widget.*
import com.simon.top10movies.utils.Status

class MainActivity : AppCompatActivity() {

    private val movieAdapter by lazy { MovieAdapter() }
    private lateinit var movieViewModel: MovieViewModel

    private lateinit var loadingIndicator: ProgressBar
    private lateinit var errorLayout: LinearLayout
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingIndicator = findViewById(R.id.progressBar)
        errorLayout = findViewById(R.id.errorLayout)
        errorText = findViewById(R.id.errorText)

        errorLayout.visibility = INVISIBLE
        loadingIndicator.visibility = VISIBLE

        setupMovieViewModel()
        setupRecyclerView()
        setupObservers()

        movieViewModel.getPopularMovies()
    }

    private fun setError(text: String){
        errorLayout.visibility = VISIBLE
        errorText.text = text;
    }

    private fun setupMovieViewModel(){
        val movieRepo = MovieDbRepo()
        val movieViewModelFactory = MovieViewModelFactory(movieRepo)
        movieViewModel = ViewModelProvider(this, movieViewModelFactory).get(MovieViewModel::class.java)
    }

    private fun setupObservers(){
        movieViewModel.popularMoviesResponse.observe(this, Observer { response ->
            loadingIndicator.visibility = INVISIBLE

            if (response.status == Status.SUCCESS) {
                response.data!!.results.let {
                    movieAdapter.setData(it.subList(0, 10))
                }
            }
            else {
                setError(getString(R.string.load_movies_error))
            }
        })

        movieViewModel.movieDetailsResponse.observe(this, Observer { response ->
            if (response.status == Status.SUCCESS) {
                val movieSheet = MovieSheetFragment()
                movieSheet.setData(response.data!!)
                movieSheet.show(supportFragmentManager, "movieSheet")
            }
            else {
                setError(getString(R.string.load_movies_error))

            }
        })
    }

    private fun setupRecyclerView() {
        movieAdapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(movieId: Int) {
                movieViewModel.getMovieDetails(movieId)
            }
        })

        val moviesRecyclerView = findViewById<RecyclerView>(R.id.moviesRecyclerView)
        moviesRecyclerView.adapter = movieAdapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}