package com.moe.spyl.di

import com.moe.spyl.BuildConfig
import com.moe.spyl.data.core.networking.RestApi
import com.moe.spyl.data.core.repository.SpylRepositoryImpl
import com.moe.spyl.data.core.domain.repository.SpylRepository
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
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            // .addInterceptor(logging) // if needed
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL) // define via data/build.gradle or app’s BuildConfig
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMyApi(retrofit: Retrofit): RestApi =
        retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun provideSpylRepository(restApi: RestApi): SpylRepository = SpylRepositoryImpl(restApi)
}