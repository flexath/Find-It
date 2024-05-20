package com.flexath.findit.auth.domain.use_cases.utils

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMsg: String? = null
)
