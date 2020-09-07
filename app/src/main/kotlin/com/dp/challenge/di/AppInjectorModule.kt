package com.dp.challenge.di

import com.dp.challenge.movielist.MovieListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppInjectorModule {

    @ContributesAndroidInjector
    abstract fun contributesMovieListActivity(): MovieListActivity
}
