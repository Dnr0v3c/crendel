package com.example.volunteerapp.domain.volunteer

import com.example.volunteerapp.profile.ProfileDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.basicAuth
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class VolunteerNetwork {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }


    suspend fun getVolunteers():Result<List<ProfileDTO>> = withContext(Dispatchers.IO){
        runCatching {
            val result = client.get("http://10.0.2.2:8081/api/volunteer/"){
                basicAuth("gnazarov","admin")
            }
            result.body()
        }
    }

}