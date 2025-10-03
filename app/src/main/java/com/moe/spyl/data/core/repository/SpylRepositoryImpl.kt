package com.moe.spyl.data.core.repository

import com.moe.spyl.data.core.networking.RestApi
import com.moe.spyl.data.core.domain.repository.SpylRepository

class SpylRepositoryImpl(private val restApi: RestApi) : SpylRepository {
    override suspend fun testDoNetworkCall() {
        TODO("Not yet implemented")
    }
}