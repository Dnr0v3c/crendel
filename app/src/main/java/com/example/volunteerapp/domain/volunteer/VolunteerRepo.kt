package com.example.volunteerapp.domain.volunteer

import com.example.volunteerapp.profile.ProfileEntity

interface VolunteerRepo {
    suspend fun getVolunteers():Result<List<ProfileEntity>>
}