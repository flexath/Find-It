package com.flexath.auth.di


import com.flexath.auth.use_cases.registration.PasswordValidation
import com.flexath.auth.use_cases.registration.RepeatedPasswordValidation
import com.flexath.auth.use_cases.registration.UserNameValidation
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
        emailValidation: com.flexath.auth.use_cases.registration.EmailValidation,
        passwordValidation: PasswordValidation,
        repeatedPasswordValidation: RepeatedPasswordValidation
    ): com.flexath.auth.use_cases.RegistrationUseCases {
        return com.flexath.auth.use_cases.RegistrationUseCases(
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
    fun provideEmailValidation(): com.flexath.auth.use_cases.registration.EmailValidation {
        return com.flexath.auth.use_cases.registration.EmailValidation()
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