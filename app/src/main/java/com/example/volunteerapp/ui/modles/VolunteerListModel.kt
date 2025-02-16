package com.example.volunteerapp.ui.modles

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volunteerapp.domain.volunteer.VolunteerNetwork
import com.example.volunteerapp.domain.volunteer.VolunteerRepoImpl
import com.example.volunteerapp.profile.ProfileEntity
import com.example.volunteerapp.profile.ProfileNetwork
import kotlinx.coroutines.launch

class VolunteerListModel:ViewModel() {

    private val volunteerNetwork = VolunteerNetwork()
    private val volunteerRepoImpl = VolunteerRepoImpl(volunteerNetwork)

    val listVolunteer:MutableLiveData<List<ProfileEntity>> by lazy {
        MutableLiveData<List<ProfileEntity>>()
    }
    init {
        getListUser()
    }

    fun getListUser(){
        viewModelScope.launch {
            val result = volunteerRepoImpl.getVolunteers()
            result.onSuccess { listVolunteers->
                listVolunteer.value = listVolunteers
                Log.d("MyVolunteers",listVolunteers.toString())

            }
            result.onFailure {
                Log.d("BLA","BLYA")
            }
        }
    }
}