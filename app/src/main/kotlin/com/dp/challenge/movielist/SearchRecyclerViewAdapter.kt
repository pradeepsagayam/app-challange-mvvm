package com.dp.challenge.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dp.challenge.R
import com.dp.challenge.movielist.SearchRecyclerViewAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_search_list_view.view.*
import javax.inject.Inject

class SearchRecyclerViewAdapter @Inject constructor() :
    RecyclerView.Adapter<ViewHolder>() {

    private val elements = mutableListOf<String>()
    private lateinit var onSearchItemClickListener: OnSearchItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_list_view, parent, false)
        return ViewHolder(layout, onSearchItemClickListener)
    }

    override fun getItemCount(): Int {
        return elements.count()
    }

    fun setItems(items: List<String>) {
        elements.clear()
        elements.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnSearchItemClickListener(onSearchItemClickListener: OnSearchItemClickListener) {
        this.onSearchItemClickListener = onSearchItemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.configure(elements[position])
    }

    class ViewHolder(
        private val view: View,
        private val onSearchItemClickListener: OnSearchItemClickListener
    ) : RecyclerView.ViewHolder(view) {

        fun configure(movieName: String) {
            view.searchValue.text = movieName

            view.setOnClickListener {
                onSearchItemClickListener.onSearchItemClick(movieName)
            }
        }
    }

    interface OnSearchItemClickListener {

        fun onSearchItemClick(query: String)
    }
}
