package com.moe.spyl.data.core.networking

import retrofit2.http.GET

interface RestApi {
    @GET("test")
    suspend fun testDoNetworkCall()
}