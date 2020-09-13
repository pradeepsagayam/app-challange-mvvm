package com.dp.challenge.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.rxjava2.RxPagingSource
import com.dp.challenge.movielist.*
import com.dp.challenge.movielist.item.MovieListItemDataModel
import com.dp.db.search.di.RecentSearchDatabaseLibrary
import com.dp.db.search.repository.RecentSearchRepository
import com.dp.movies.service.di.MoviesApiLibrary
import com.dp.network.NetworkConfiguration
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import kotlin.reflect.KClass

@Module
abstract class AppModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindGetMovieListRxRepository(repositoryImpl: GetMovieListRxRepositoryImpl)
            : GetMovieListRxRepository

    @Binds
    abstract fun bindRxPagingSource(moviesPagingSource: MoviesPagingSource)
            : RxPagingSource<MovieDetailsRequest, MovieListItemDataModel>

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindMovieListViewModel(movieListViewModel: MovieListViewModel): ViewModel

    companion object {

        @Provides
        fun providesMoviesProvider(library: MoviesApiLibrary) =
            library.moviesProvider()

        @Provides
        fun providesMoviesApiLibrary(
            retrofit: Retrofit,
            networkConfiguration: NetworkConfiguration
        ) =
            MoviesApiLibrary(retrofit, networkConfiguration)

        @Provides
        @JvmStatic
        fun providesRecentSearchDatabaseLibrary(context: Context): RecentSearchDatabaseLibrary {
            return RecentSearchDatabaseLibrary(context)
        }

        @Provides
        @JvmStatic
        fun providesRecentSearchRepository(recentSearchDatabaseLibrary: RecentSearchDatabaseLibrary): RecentSearchRepository {
            return recentSearchDatabaseLibrary.recentSearchRepository()
        }

    }

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}
