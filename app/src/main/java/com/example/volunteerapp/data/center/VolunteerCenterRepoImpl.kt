package com.example.volunteerapp.data.center

import com.example.volunteerapp.data.VolunteerCenterEntity
import com.example.volunteerapp.domain.VolunteerCenterNetwork

class VolunteerCenterRepoImpl(val volunteerCenterNetwork: VolunteerCenterNetwork):VolunteerCenterRepo{
    override suspend fun getCenters(): Result<List<VolunteerCenterEntity>> {
        return volunteerCenterNetwork.getCenters().map { listDto ->
            listDto.mapNotNull { dto ->
                VolunteerCenterEntity(
                    id = dto.id?: return@mapNotNull null,
                    name = dto.name?: return@mapNotNull null,
                    coordinates = dto.coordinates?: return@mapNotNull null
//                    photoUrl = dto.photoUrl ?: return@mapNotNull null
                )
            }
        }
    }


}