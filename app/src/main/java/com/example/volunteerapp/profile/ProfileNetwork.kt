package com.example.volunteerapp.profile

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.basicAuth
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
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
//        install(Auth){
//            basic {
//                sendWithoutRequest { true }
//                credentials {
//                    BasicAuthCredentials(username = "gnazarov", password = "admin")
//                }
//
//            }
//        }




    }

    suspend fun getUser(login:String):Result<ProfileDTO> = withContext(Dispatchers.IO) {
        runCatching {
            val result = client.get("http://10.0.2.2:8081/api/volunteer/username/${login}")
            result.body()
        }
    }
    suspend fun updateUser(id:Long,name:String,email:String,username:String):Result<ProfileDTO> = withContext(Dispatchers.IO){
        runCatching {
            var result = client.put("http://10.0.2.2:8081/api/volunteer/${id}") {
                basicAuth("gnazarov","admin")
                contentType(ContentType.Application.Json)
                setBody(ProfileDTO(id = id, name = name, email = email, username = username))
            }
            result.body()
        }
    }




}