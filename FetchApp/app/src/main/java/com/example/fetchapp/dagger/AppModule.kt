package com.example.fetchapp.dagger

import com.example.fetchapp.network.ApiCallExecutor
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
    fun provideApiCallExecutor(): ApiCallExecutor {
        return ApiCallExecutor()
    }
}