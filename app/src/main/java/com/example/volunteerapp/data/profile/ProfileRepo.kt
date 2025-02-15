package com.example.volunteerapp.data.profile

import com.example.volunteerapp.profile.ProfileEntity

interface ProfileRepo {
    suspend fun getUser(login:String):Result<ProfileEntity>
}