package com.dp.movies.service

import com.dp.movies.service.model.MoviesResponse
import com.dp.network.NetworkConfiguration
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesProviderTest {

    @InjectMocks
    private lateinit var subject: MoviesProvider

    @Mock
    private lateinit var networkConfiguration: NetworkConfiguration

    @Mock
    private lateinit var moviesService: MoviesService

    @Mock
    private lateinit var moviesResultMapper: MoviesResultMapper

    @Mock
    private lateinit var response: MoviesResponse

    @Mock
    private lateinit var success: MoviesResult.Success

    @Before
    fun setUp() {
        `when`(networkConfiguration.getApiKey()).thenReturn("apiKey")
        `when`(moviesService.getMovies("apiKey", "query", 1))
            .thenReturn(Observable.just(response))
        `when`(moviesResultMapper.mapFrom(response)).thenReturn(success)
    }

    @Test
    fun getUserDetails_verifyServiceCall() {
        subject.getMovies("query", 1).test()

        verify(moviesService).getMovies("apiKey", "query", 1)
    }

    @Test
    fun getUserDetails_givenServiceWithResponse_verifyMapperCall() {
        subject.getMovies("query", 1).test()

        verify(moviesResultMapper).mapFrom(response)
    }

    @Test
    fun getUserDetails_givenResponseWithData_returnSuccessResultWithData() {
        val actual = subject.getMovies("query", 1).test()

        actual.assertValue { result -> (result is MoviesResult.Success) && result == success }
    }

    @Test
    fun getUserDetails_givenErrorResponse_returnFailureResult() {
        val throwable = Throwable()
        `when`(moviesService.getMovies("apiKey", "query", 1))
            .thenReturn(Observable.error(throwable))

        val actual = subject.getMovies("query", 1).test()

        actual.assertValue { result -> result is MoviesResult.Failure }
        actual.assertValue { result -> (result as MoviesResult.Failure).throwable == throwable }
    }
}
