# app-challange-mvvm
This is an example of Android application using TDD and MVVM design pattern to show the Movies List with Search.It is written 100% in Kotlin with both Espresso and Unit tests.
This Challenge covers below concepts such as:

1. TDD architecture
2. MVVM design pattern
3. Dagger dependency injection
4. Kotlin code architecture
5. ReactiveX for Async operations
6. Clean code concept
7. JUnit and Espresso Tests

Features:

Neat build.gradle with shared dependencies.

Dagger2 with android component. Injectable Views and structure to make the app testable and maintanable.

State management with RxKotlin and Android Components - background-foreground and orientation changes will connect to the ongoing network request.

Network requests continue in the background - UI is capable of retrieval of the current state when resumed.

Custom retrofit call factory to handle no connection error in a reactive way.

Espresso tests with overridden dependencies and MockWebServer.

Easy debuggable builds.

## Languages, libraries and tools used in this project

* [Kotlin](https://kotlinlang.org/)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* [Android Support Libraries](https://developer.android.com/topic/libraries/support-library)
* [Dagger 2](https://github.com/google/dagger)
* [RxKotlin](https://github.com/ReactiveX/RxKotlin)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [RxBinding](https://github.com/JakeWharton/RxBinding)
* [Retrofit](http://square.github.io/retrofit/)
* [room](https://developer.android.com/topic/libraries/architecture/room)
* [places](https://developers.google.com/places/web-service/overview)
* [OkHttp](http://square.github.io/okhttp/)
* [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
* [AssistedInject](https://github.com/square/AssistedInject)
* [Gson](https://github.com/google/gson)
* [junit](https://developer.android.com/training/testing/junit-rules)
* [Mockito](http://site.mockito.org/)
* [Espresso](https://developer.android.com/training/testing/espresso/index.html)
* [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)

