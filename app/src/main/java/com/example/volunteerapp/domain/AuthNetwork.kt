package com.example.volunteerapp.domain

import com.example.volunteerapp.data.AuthRegisterDto
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.Credentials

class AuthNetwork {


    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })

        }
//        install(Auth){
//            basic {
//                sendWithoutRequest { true }
//                credentials {
//                    BasicAuthCredentials(username = "admin", password = "admin")
//                }
//
//            }
//        }

    }

    suspend fun login(login:String,password:String):Result<Unit> = withContext(Dispatchers.IO){
        runCatching {
            val token = Credentials.basic(login,password)
            val result = client.get("http://10.0.2.2:8081/api/volunteer/login"){
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if(result.status!= HttpStatusCode.OK){
                error("Status ${result.status}")

            }

            Unit

        }
    }
    suspend fun isUserExist(login:String):Result<Boolean> = withContext(Dispatchers.IO){
        runCatching {
            val result = client.get("http://10.0.2.2:8081/api/volunteer/username/$login")
            result.status==HttpStatusCode.OK

        }
    }
    suspend fun register(username:String,name: String,email:String,password: String):Result<Unit> = withContext(Dispatchers.IO){
        runCatching {
            val result = client.post("http://10.0.2.2:8081/api/volunteer/register"){
                contentType(ContentType.Application.Json)
                setBody(
                    AuthRegisterDto(
                        username = username,
                        name=name,
                        password=password,
                        email=email

                    )
                )
            }
            if(result.status!=HttpStatusCode.OK){
                error("Status ${result.status}")
            }

            Unit


        }
    }
}