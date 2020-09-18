package com.techadhoc.mvvmkoin.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RepositoryInterface {
    suspend fun giveHello(): String
}

class Repository() : RepositoryInterface {
    override suspend fun giveHello() =
        withContext(Dispatchers.IO) {
            "Hello Koin"
        }
}