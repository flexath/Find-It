package com.flexath.auth.use_cases.utils

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMsg: String? = null
)
