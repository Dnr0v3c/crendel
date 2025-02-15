package com.example.volunteerapp.domain.center

import com.example.volunteerapp.data.profile.ProfileRepoImpl

class GetProfileUseCase(val profileRepoImpl: ProfileRepoImpl) {
    suspend operator fun invoke(login:String) = profileRepoImpl.getUser(login)
}