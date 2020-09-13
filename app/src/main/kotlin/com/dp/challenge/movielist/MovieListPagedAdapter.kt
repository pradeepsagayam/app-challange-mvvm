package com.dp.challenge.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dp.challenge.databinding.ItemMovieListViewBinding
import com.dp.challenge.movielist.item.MovieListItemDataModel
import com.dp.challenge.movielist.item.MovieListItemViewHolder
import javax.inject.Inject

class MovieListPagedAdapter @Inject constructor(
    diffCallback: MovieDetailsUtilItemCallback
) : PagingDataAdapter<MovieListItemDataModel, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieListViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieListItemViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieListItemViewHolder) {
            holder.bindView(getItem(position))
        }
    }
}
