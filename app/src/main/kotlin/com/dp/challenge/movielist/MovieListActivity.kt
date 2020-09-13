package com.dp.challenge.movielist

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import com.dp.baseui.BaseActivity
import com.dp.challenge.R
import com.dp.challenge.databinding.ActivityMovieListBinding
import com.dp.challenge.di.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.movie_list_error.*
import javax.inject.Inject


class MovieListActivity : BaseActivity(),
    LifecycleOwner,
    SearchRecyclerViewAdapter.OnSearchItemClickListener,
    SearchView.OnQueryTextListener,
    SearchView.OnCloseListener,
    View.OnFocusChangeListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var pageAdapter: MovieListPagedAdapter

    @Inject
    lateinit var searchAdapter: SearchRecyclerViewAdapter

    private lateinit var movieListViewModel: MovieListViewModel

    override val toolbarTitle: Int get() = R.string.title_movie_list

    override val layoutResource: Int get() = R.layout.activity_movie_list

    override fun navigateUp() {
        onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        movieListViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MovieListViewModel::class.java)
        lifecycle.addObserver(movieListViewModel)
        (viewDataBinding as ActivityMovieListBinding).lifecycleOwner = this
        setupSearchView()
        updateMovieList()
        updateSearchList()
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
        searchView.setOnQueryTextFocusChangeListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchView.postDelayed({ hideKeyboard() }, 500)
        movieListViewModel.getMovies(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        onSearchTextChange(newText)
        return true
    }

    override fun onClose(): Boolean {
        moviesRecyclerView.adapter = searchAdapter
        searchAdapter.notifyDataSetChanged()
        return false
    }

    override fun onSearchItemClick(query: String) {
        searchView.postDelayed({ hideKeyboard() }, 500)
        searchView.setQuery(query, true)
    }

    override fun onFocusChange(view: View?, isFocused: Boolean) {
        if (isFocused) {
            movieListViewModel.onSearchTextChange(searchView.query.toString())
        }
    }

    private fun onSearchTextChange(newText: String?) {
        movieListViewModel.onSearchTextChange(newText)
    }

    private fun updateSearchList() {
        searchAdapter.setOnSearchItemClickListener(this)
        movieListViewModel.filteredSearchElements.observe(this, Observer {
            moviesRecyclerView.adapter = searchAdapter
            searchAdapter.setItems(it)
        })
    }

    private fun updateMovieList() {
        movieListViewModel.movieViewModels.observe(this, Observer {
            moviesRecyclerView.adapter = pageAdapter
            pageAdapter.submitData(lifecycle, it)
        })

        pageAdapter.addLoadStateListener { loadStates ->
            if (loadStates.refresh is Loading) {
                showLoadingDialog(false)
            } else {
                hideLoadingDialog()
            }

            if (loadStates.refresh is LoadState.Error) {
                genericErrorContainer.visibility = VISIBLE
                moviesRecyclerView.visibility = GONE
            } else {
                genericErrorContainer.visibility = GONE
                moviesRecyclerView.visibility = VISIBLE
            }
        }
    }
}
