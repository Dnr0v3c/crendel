package com.example.volunteerapp.domain.center

import com.example.volunteerapp.data.center.VolunteerCenterRepo

class GetCenterUseCase(val volunteerCenterRepo: VolunteerCenterRepo) {
    suspend operator fun invoke() = volunteerCenterRepo.getCenters()


}