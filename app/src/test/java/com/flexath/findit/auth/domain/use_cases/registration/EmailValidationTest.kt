package com.flexath.findit.auth.domain.use_cases.registration

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class EmailValidationTest {
    private lateinit var emailValidation: EmailValidation

    @Before
    fun setUp() {
        emailValidation = EmailValidation()
    }

    @Test
    fun `If email is empty, return false`() {
        val result = emailValidation.invoke("").isSuccessful
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `If email is not formatted correctly, return false`() {
        val result = emailValidation.invoke("aungthiha@gmail").isSuccessful
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `If email is formatted correctly, return true`() {
        val result = emailValidation.invoke("aungthiha@gmail.com").isSuccessful
        Truth.assertThat(result).isTrue()
    }
}