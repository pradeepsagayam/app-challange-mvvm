package com.dp.challenge

import android.content.Context
import com.dp.network.NetworkConfiguration
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

class NetworkConfigurationImpl(private val context: Context) : NetworkConfiguration {

    override fun getApiKey(): String = "b0981f2db913dedb3f90b88a68de3a1f"

    override fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    override fun ioScheduler(): Scheduler = Schedulers.io()

    override fun getHost(): String {
        return "http://api.themoviedb.org/3/search/"
    }

    override fun getCacheDir(): File = context.cacheDir

    override fun getCacheSize(): Long = 10 * 1024 * 1024

    override fun getTimeoutSeconds(): Long = 60
}
