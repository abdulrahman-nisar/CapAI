package com.example.capai.di

import com.example.capai.data.repository.CapAIRepositoryImpl
import com.example.capai.domain.repository.CapAiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCapAiRepository(
        impl : CapAIRepositoryImpl
    ): CapAiRepository
}