package com.example.codingexamui.core.di

import com.example.codingexamui.domain.use_case.AuthUseCase
import com.example.codingexamui.domain.use_case.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(): AuthUseCase {
        return AuthUseCase(loginUseCase = LoginUseCase())
    }
}