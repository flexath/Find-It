package com.flexath.findit.auth.domain.use_cases.registration


import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class UserNameValidationTest {

    private lateinit var userNameValidation: UserNameValidation

    @Before
    fun setUp() {
        userNameValidation = UserNameValidation()
    }

    @Test
    fun `If user name is empty`() {
        val result = userNameValidation.invoke("").isSuccessful
        assertThat(result).isFalse()
    }

    @Test
    fun `If user name is not empty`() {
        val result = userNameValidation.invoke("Aung Thiha").isSuccessful
        assertThat(result).isTrue()
    }
}