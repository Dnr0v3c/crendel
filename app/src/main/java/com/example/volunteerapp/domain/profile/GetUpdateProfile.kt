package com.example.volunteerapp.domain.profile

import com.example.volunteerapp.data.profile.ProfileRepo

class GetUpdateProfile(val profileRepo: ProfileRepo) {
    suspend operator fun invoke(id:Long,name:String,email:String,username:String) = profileRepo.updateUser(id,name,email,username)
}