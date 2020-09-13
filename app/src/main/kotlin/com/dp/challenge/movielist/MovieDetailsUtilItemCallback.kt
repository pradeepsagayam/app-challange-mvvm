package com.dp.challenge.movielist

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.dp.challenge.movielist.item.MovieListItemDataModel
import javax.inject.Inject

class MovieDetailsUtilItemCallback @Inject constructor() : ItemCallback<MovieListItemDataModel>() {

    override fun areItemsTheSame(
        oldItem: MovieListItemDataModel,
        newItem: MovieListItemDataModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MovieListItemDataModel,
        newItem: MovieListItemDataModel
    ): Boolean {
        return oldItem.title == newItem.title
    }
}
