package com.dp.challenge.di

import com.dp.challenge.AppChallengeApplication
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppConfigModule::class,
        AppModule::class,
        AppInjectorModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(appChallengeApplication: AppChallengeApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(appChallengeApplication: AppChallengeApplication)

}

//@AssistedModule
//@Module(includes = [AssistedInject_AssistedInjectModule::class])
//interface AssistedInjectModule
