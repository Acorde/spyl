package com.moe.spyl.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    @Singleton
//    fun provideApi(okHttp: OkHttpClient): RestApi = retrofitBuilder(okHttp).create(MyApi::class.java)
}