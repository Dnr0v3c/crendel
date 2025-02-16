package com.example.volunteerapp.ui.modles

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volunteerapp.data.profile.ProfileRepoImpl
import com.example.volunteerapp.domain.profile.GetProfileUseCase
import com.example.volunteerapp.domain.profile.GetUpdateProfile
import com.example.volunteerapp.profile.ProfileNetwork
import kotlinx.coroutines.launch

class ProfileView:ViewModel() {

    private val profileNetwork:ProfileNetwork = ProfileNetwork()
    private val profileRepoImpl:ProfileRepoImpl = ProfileRepoImpl(profileNetwork)
    private val getProfileUseCase: GetProfileUseCase = GetProfileUseCase(profileRepoImpl)
    private val getUpdateProfile:GetUpdateProfile  =GetUpdateProfile(profileRepoImpl)

    var id:Long = 0

    val username:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val password:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val name:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val email:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    init {
        getUser(login = username.toString())
    }

     fun getUser(login:String){
        viewModelScope.launch {
            var result = getProfileUseCase(login)
            result.onSuccess { profile->
                name.value = profile.name
                email.value = profile.email
                id = profile.id
                Log.d("MiId",id.toString())

            }

        }
    }
    fun updateUser(name1:String,email1:String){
        viewModelScope.launch {
            var result = getUpdateProfile(id = id,name=name1,email=email1, username = username.value.toString())
            result.onSuccess {profile->
                name.value = profile.name
                email.value=profile.email

            }
            result.onFailure {
                Log.d("Jopa","Kopa")
            }
        }
    }



}