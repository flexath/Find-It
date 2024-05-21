package com.flexath.findit.auth.domain.use_cases.registration

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class RepeatedPasswordValidationTest {
    private lateinit var repeatedPasswordValidation: RepeatedPasswordValidation

    @Before
    fun setUp() {
        repeatedPasswordValidation = RepeatedPasswordValidation()
    }

    @Test
    fun `If repeated password is empty, return false`() {
        val result = repeatedPasswordValidation.invoke("","").isSuccessful
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `If repeated password is not the same as password, return false`() {
        val result = repeatedPasswordValidation.invoke("asdfjk9","asdfghjk10").isSuccessful
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `If repeated password is the same as password, return true`() {
        val result = repeatedPasswordValidation.invoke("asdfghjk9","asdfghjk9").isSuccessful
        Truth.assertThat(result).isTrue()
    }
}