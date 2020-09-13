package com.dp.db.search

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MockRecentSearchEntityProviderTest {

    @InjectMocks
    private lateinit var subject: MockLoginDetailsProvider

    @Test
    fun get() {
        val actual = subject.get()

        assertThat(actual.username).isEqualTo("admin@gmail.com")
        assertThat(actual.password).isEqualTo("Qwerty@123")
    }
}
