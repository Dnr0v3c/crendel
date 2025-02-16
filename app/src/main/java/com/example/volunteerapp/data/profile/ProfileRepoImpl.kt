package com.example.volunteerapp.data.profile

import com.example.volunteerapp.profile.ProfileEntity
import com.example.volunteerapp.profile.ProfileNetwork

class ProfileRepoImpl(val profileNetwork: ProfileNetwork):ProfileRepo {
    override suspend fun getUser(login: String): Result<ProfileEntity> {
        val result:Result<ProfileEntity> = profileNetwork.getUser(login).map { profileDto->
            ProfileEntity(id = profileDto.id, name = profileDto.name, email = profileDto.email, username = profileDto.username)
        }
        return result

    }

    override suspend fun updateUser(
        id: Long,
        name: String,
        email: String,
        username: String
    ): Result<ProfileEntity> {
        var result:Result<ProfileEntity> = profileNetwork.updateUser(id,name,email,username).map { profileDto->
            ProfileEntity(id=profileDto.id,name=profileDto.name,username=profileDto.username,email=profileDto.email)

        }
        return result
    }
}