package com.example.volunteerapp.domain

import com.example.volunteerapp.domain.center.VolunteerCenterDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class VolunteerCenterNetwork {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }



    }

    suspend fun getCenters(): Result<List<VolunteerCenterDto>> = withContext(Dispatchers.IO) {

        runCatching {
            val result = client.get("http://10.0.2.2:8081/api/volunteercenter")
            if (result.status != HttpStatusCode.OK) {


                error("Status ${result.status}")
            }
            result.body()
        }


    }

}