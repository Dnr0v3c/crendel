package com.example.volunteerapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRegisterDto(
    @SerialName("username")
    val username:String,
    @SerialName("name")
    val name:String,
    @SerialName("password")
    val password:String,
    @SerialName("email")
    val email:String
)