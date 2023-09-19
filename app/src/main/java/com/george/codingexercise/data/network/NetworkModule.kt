package com.george.codingexercise.data.network

import com.george.codingexercise.data.RepositoryImpl
import com.george.codingexercise.domain.Repository
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
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    }

    @Provides
    fun provideFetchApiService(retrofit: Retrofit): FetchApiService {
        return retrofit.create(FetchApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: FetchApiService): Repository {
        return RepositoryImpl(apiService)
    }
}