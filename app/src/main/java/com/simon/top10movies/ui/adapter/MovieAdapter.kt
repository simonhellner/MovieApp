package com.simon.top10movies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simon.top10movies.utils.Constants.BACKDROP_BASE_URL
import com.simon.top10movies.data.model.Movie
import com.simon.top10movies.R

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList: List<Movie> = emptyList()
    private lateinit var onClickListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(movieId: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_item_layout, parent, false)
        return ViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.movieName.text = movieList[position].title;
        holder.movieId = movieList[position].id

        Glide
            .with(holder.itemView.getContext())
            .load(BACKDROP_BASE_URL + movieList[position].backdrop_path)
            .into(holder.movieBackdrop)
    }

    override fun getItemCount(): Int {
        return movieList.size;
    }

    fun setData(movieList: List<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    class ViewHolder(ItemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val movieName: TextView = itemView.findViewById(R.id.movieItemTitle)
        val movieBackdrop: ImageView = itemView.findViewById(R.id.movieItemBackdrop)
        var movieId: Int = 0

        init{
            itemView.setOnClickListener {
                listener.onItemClick(movieId)
            }
        }
    }

}