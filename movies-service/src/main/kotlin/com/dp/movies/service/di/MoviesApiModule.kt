package com.dp.movies.service.di

import com.dp.movies.service.MoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MoviesApiModule {

    @Provides
    fun providesUserDetailsService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)
}
