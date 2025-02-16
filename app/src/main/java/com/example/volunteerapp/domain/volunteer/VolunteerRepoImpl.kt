package com.example.volunteerapp.domain.volunteer

import com.example.volunteerapp.profile.ProfileEntity

class VolunteerRepoImpl(val volunteerNetwork: VolunteerNetwork):VolunteerRepo {
    override suspend fun getVolunteers(): Result<List<ProfileEntity>> {
        return volunteerNetwork.getVolunteers().map { listDto->
            listDto.mapNotNull {dto->
                ProfileEntity(id = dto.id, name = dto.name, username = dto.username, email = dto.email)

            }
        }
    }
}