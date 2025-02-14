package com.example.volunteerapp.data.center

import com.example.volunteerapp.data.VolunteerCenterEntity
import com.example.volunteerapp.domain.center.VolunteerCenterDto

interface VolunteerCenterRepo {
    suspend fun getCenters():Result<List<VolunteerCenterEntity>>
    suspend fun getCentersCoord(coordinates:String):Result<VolunteerCenterEntity>


}