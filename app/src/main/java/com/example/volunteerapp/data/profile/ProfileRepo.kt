package com.example.volunteerapp.data.profile

import com.example.volunteerapp.profile.ProfileEntity

interface ProfileRepo {
    suspend fun getUser(login:String):Result<ProfileEntity>
    suspend fun updateUser(id:Long,name:String,email:String,username:String):Result<ProfileEntity>

}