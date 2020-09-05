package com.dp.challenge.di

import android.content.Context
import android.content.res.Resources
import com.dp.baseui.ResourceProvider
import com.dp.challenge.AppChallengeApplication
import com.dp.challenge.NetworkConfigurationImpl
import com.dp.network.NetworkConfiguration
import com.dp.network.di.NetworkLibrary
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class AppConfigModule {

    @Provides
    fun providesContext(application: AppChallengeApplication): Context = application.applicationContext

    @Provides
    fun providesResourceProvider(context: Context): ResourceProvider =
        ResourceProvider(context)

    @Provides
    fun providesResources(application: AppChallengeApplication): Resources =
        application.applicationContext.resources

    @Provides
    fun providesNetworkLibrary(context: Context, networkConfiguration: NetworkConfiguration) =
        NetworkLibrary(context, networkConfiguration)

    @Provides
    fun providesNetworkConfiguration(context: Context): NetworkConfiguration =
        NetworkConfigurationImpl(context)

    @Provides
    fun providesRetrofit(
        networkLibrary: NetworkLibrary
    ): Retrofit {
        val okHttpBuilder = networkLibrary.okHttpClient()
            .newBuilder()

        val okHttpClient = okHttpBuilder
            .build()
        return networkLibrary.retrofit().newBuilder().client(okHttpClient).build()
    }

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}
