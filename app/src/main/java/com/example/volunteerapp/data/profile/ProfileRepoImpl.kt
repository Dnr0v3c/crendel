package com.example.volunteerapp.data.profile

import com.example.volunteerapp.profile.ProfileEntity
import com.example.volunteerapp.profile.ProfileNetwork

class ProfileRepoImpl(val profileNetwork: ProfileNetwork):ProfileRepo {
    override suspend fun getUser(login: String): Result<ProfileEntity> {
        val result:Result<ProfileEntity> = profileNetwork.getUser(login).map { profileDto->
            ProfileEntity(name = profileDto.name, email = profileDto.email, username = profileDto.username)
        }
        return result

    }
}