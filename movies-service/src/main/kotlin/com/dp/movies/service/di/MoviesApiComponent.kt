package com.dp.movies.service.di

import com.dp.network.NetworkConfiguration
import com.dp.movies.service.MoviesProvider
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [MoviesApiModule::class])
interface MoviesApiComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun retrofit(retrofit: Retrofit): Builder

        @BindsInstance
        fun networkConfiguration(networkConfiguration: NetworkConfiguration): Builder

        fun build(): MoviesApiComponent
    }

    fun moviesProvider(): MoviesProvider
}
