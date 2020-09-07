package com.dp.challenge.movielist

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.dp.challenge.R
import com.dp.challenge.databinding.ItemMovieListViewBinding

internal class MovieListItemViewHolder(private val binding: ItemMovieListViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(dataModel: MovieListItemDataModel?) {
        if (null != dataModel) {
            configureImage(dataModel.bannerUrl)
            binding.movieName.text = dataModel.title
            binding.movieReleaseDate.text = dataModel.releaseDate
            binding.movieOverView.text = dataModel.overView
        }
    }

    private fun configureImage(bannerUrl: String?) {
        if (null != bannerUrl) {
            Glide.with(binding.root.context).load(bannerUrl)
                .apply(RequestOptions.placeholderOf(R.color.dark_grey))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.movieBanner)
        }
    }
}
