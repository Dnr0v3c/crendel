package com.example.volunteerapp

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapView :ViewModel() {

    val message:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val request1:MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

}