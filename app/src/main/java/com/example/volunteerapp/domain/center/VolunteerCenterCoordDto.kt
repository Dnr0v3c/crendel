package com.example.volunteerapp.domain.center

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class VolunteerCenterCoordDto(
    @SerialName("coordinates")
    val coordinates:String
)