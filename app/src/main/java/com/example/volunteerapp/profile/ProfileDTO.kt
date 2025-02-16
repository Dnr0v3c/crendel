package com.example.volunteerapp.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDTO (
    @SerialName("id")
    val id:Long,
    @SerialName("name")
    val name:String,
    @SerialName("username")
    val username:String,
    @SerialName("email")
    val email:String
)