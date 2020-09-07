package com.dp.challenge.movielist

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dp.baseui.BaseActivity
import com.dp.challenge.R
import com.dp.challenge.databinding.ActivityMovieListBinding
import com.dp.challenge.di.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_list.*
import javax.inject.Inject

class MovieListActivity : BaseActivity(), LifecycleOwner {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: MovieListPagedAdapter

    private lateinit var movieListViewModel: MovieListViewModel

    override val toolbarTitle: Int get() = R.string.title_movie_list

    override val layoutResource: Int get() = R.layout.activity_movie_list

    override fun enableCloseButton(): Boolean {
        return true
    }

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
        setupRecyclerView()

        movieListViewModel.viewModels.observe(this, Observer {
            adapter.submitData(lifecycle, it)
        })
    }

    private fun setupRecyclerView() {
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.adapter = adapter
    }
}
