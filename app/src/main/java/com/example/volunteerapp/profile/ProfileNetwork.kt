package com.example.volunteerapp.profile

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ProfileNetwork {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }



    }

    suspend fun getUser(login:String):Result<ProfileDTO> = withContext(Dispatchers.IO) {
        runCatching {
            val result = client.get("http://10.0.2.2:8081/api/volunteer/username/${login}")
            result.body()
        }
    }



}