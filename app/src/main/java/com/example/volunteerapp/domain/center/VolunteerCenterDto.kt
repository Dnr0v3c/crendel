package com.example.volunteerapp.domain.center

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolunteerCenterDto (
    @SerialName("id")
    val id:Long,
    @SerialName("name")
    val name:String,
    @SerialName("coordinates")
    val coordinates:String
)