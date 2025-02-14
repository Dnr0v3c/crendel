package com.example.volunteerapp.domain.center

import com.example.volunteerapp.data.center.VolunteerCenterRepo

class GetCenterCoordinatesUseCase(val volunteerCenterRepo: VolunteerCenterRepo) {
    suspend operator fun invoke(coordinates:String) = volunteerCenterRepo.getCentersCoord(coordinates = coordinates)
}