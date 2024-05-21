package com.flexath.findit.auth.domain.use_cases.registration

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class PasswordValidationTest {

    private lateinit var passwordValidation: PasswordValidation

    @Before
    fun setUp() {
        passwordValidation = PasswordValidation()
    }

    @Test
    fun `If password is empty, return false`() {
        val result = passwordValidation.invoke("").isSuccessful
        assertThat(result).isFalse()
    }

    @Test
    fun `If password is less than 8, return false`() {
        val result = passwordValidation.invoke("a1dfghj").isSuccessful
        assertThat(result).isFalse()
    }

    @Test
    fun `If password does not contains only one digit, return false`() {
        val result = passwordValidation.invoke("adfghjklq").isSuccessful
        assertThat(result).isFalse()
    }

    @Test
    fun `If password does not contains only one letter, return false`() {
        val result = passwordValidation.invoke("123456789").isSuccessful
        assertThat(result).isFalse()
    }

    @Test
    fun `If password is set correctly, return true`() {
        val result = passwordValidation.invoke("asdfghj1").isSuccessful
        assertThat(result).isTrue()
    }
}