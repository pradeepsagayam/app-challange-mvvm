package com.dp.challenge

import android.app.Application
import com.dp.challenge.di.ApplicationComponent
import com.dp.challenge.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AppChallengeApplication : Application(), HasAndroidInjector {


    private val appComponent = DaggerApplicationComponent.builder().apply {
        application(this@AppChallengeApplication)
    }.build()

    override fun onCreate() {
        super.onCreate()
        buildAppComponent()
        appComponent.inject(this)
    }

    private fun buildAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder().apply {
            application(this@AppChallengeApplication)
        }.build()
    }


    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingActivityInjector

    fun getAppComponent(): ApplicationComponent {
        return appComponent
    }
}
