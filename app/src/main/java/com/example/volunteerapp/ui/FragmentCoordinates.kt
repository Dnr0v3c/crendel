package com.example.volunteerapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.volunteerapp.ui.modles.MapView
import com.example.volunteerapp.R
import com.example.volunteerapp.data.center.VolunteerCenterRepoImpl
import com.example.volunteerapp.databinding.FragmentCoordinatesBinding
import com.example.volunteerapp.domain.VolunteerCenterNetwork
import com.example.volunteerapp.domain.center.GetCenterCoordinatesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentCoordinates: Fragment(R.layout.fragment_coordinates) {
    lateinit var binding:FragmentCoordinatesBinding
    private val mapView: MapView by activityViewModels()
    lateinit var coordinates:String
    lateinit var volunteerCenterNetwork:VolunteerCenterNetwork
    lateinit var volunteerCenterRepoImpl:VolunteerCenterRepoImpl
    lateinit var getCenterCoordinatesUseCase: GetCenterCoordinatesUseCase


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCoordinatesBinding.bind(view)
        volunteerCenterNetwork = VolunteerCenterNetwork()
        volunteerCenterRepoImpl =VolunteerCenterRepoImpl(volunteerCenterNetwork)
        getCenterCoordinatesUseCase = GetCenterCoordinatesUseCase(volunteerCenterRepoImpl)

        mapView.message.observe(activity as LifecycleOwner,{
            coordinates = it
        })
        Log.d("TTT",coordinates)
        CoroutineScope(Dispatchers.Main).launch {
            getCenterByCoordinates(coordinates)
        }



    }



    suspend fun getCenterByCoordinates(coordinates:String){
        val result = volunteerCenterNetwork.getCenterByCoordinates(coordinates)
        result.onSuccess { volunteer1->
            binding.nameCenter.text = volunteer1.name
        }
        result.onFailure {
            binding.error.isVisible =true
        }

    }


    companion object{
        @JvmStatic
        fun newInstance() = FragmentCoordinates()

    }
}