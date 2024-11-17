package com.example.fetchapp.dagger

import com.example.fetchapp.BuildConfig
import com.example.fetchapp.network.FetchService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFetchService(): FetchService {
        // Should also add interceptor for various things like token, parsing, fallback handling
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient.Builder().build())
            .baseUrl(BuildConfig.FETCH_URL)
            .build()
        return retrofit.create(FetchService::class.java)
    }
}