package com.example.volunteerapp.ui.modles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volunteerapp.data.profile.ProfileRepoImpl
import com.example.volunteerapp.domain.center.GetProfileUseCase
import com.example.volunteerapp.profile.ProfileNetwork
import kotlinx.coroutines.launch

class ProfileView:ViewModel() {

    private val profileNetwork:ProfileNetwork = ProfileNetwork()
    private val profileRepoImpl:ProfileRepoImpl = ProfileRepoImpl(profileNetwork)
    private val getProfileUseCase:GetProfileUseCase = GetProfileUseCase(profileRepoImpl)



    val username:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val name:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val email:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

     fun getUser(login:String){
        viewModelScope.launch {
            var result = getProfileUseCase(login)
            result.onSuccess { profile->
                username.value= profile.username
                name.value = profile.name
                email.value = profile.email

            }

        }
    }


}