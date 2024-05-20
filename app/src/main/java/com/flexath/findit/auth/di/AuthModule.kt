package com.flexath.findit.auth.di

import com.flexath.findit.auth.domain.use_cases.RegistrationUseCases
import com.flexath.findit.auth.domain.use_cases.registration.EmailValidation
import com.flexath.findit.auth.domain.use_cases.registration.PasswordValidation
import com.flexath.findit.auth.domain.use_cases.registration.RepeatedPasswordValidation
import com.flexath.findit.auth.domain.use_cases.registration.UserNameValidation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideRegistrationUseCases(
        userNameValidation: UserNameValidation,
        emailValidation: EmailValidation,
        passwordValidation: PasswordValidation,
        repeatedPasswordValidation: RepeatedPasswordValidation
    ): RegistrationUseCases {
        return RegistrationUseCases(
            userNameValidation,
            emailValidation,
            passwordValidation,
            repeatedPasswordValidation
        )
    }

    @Provides
    fun provideUserNameValidation(): UserNameValidation {
        return UserNameValidation()
    }

    @Provides
    fun provideEmailValidation(): EmailValidation {
        return EmailValidation()
    }

    @Provides
    fun providePasswordValidation(): PasswordValidation {
        return PasswordValidation()
    }

    @Provides
    fun provideRepeatedPasswordValidation(): RepeatedPasswordValidation {
        return RepeatedPasswordValidation()
    }
}