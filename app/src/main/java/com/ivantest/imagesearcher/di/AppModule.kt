package com.ivantest.imagesearcher.di

import com.ivantest.imagesearcher.api.FlickrApiService
import com.ivantest.imagesearcher.api.data.SearchRepository
import com.ivantest.imagesearcher.api.data.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFlickrApiService(): FlickrApiService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log request and response bodies
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(FlickrApiService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(FlickrApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(flickrApiService: FlickrApiService): SearchRepository {
        return SearchRepositoryImpl(flickrApiService)
    }
}