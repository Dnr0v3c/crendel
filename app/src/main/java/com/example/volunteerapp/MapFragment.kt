package com.example.volunteerapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.volunteerapp.data.VolunteerCenterEntity
import com.example.volunteerapp.data.center.VolunteerCenterRepoImpl
import com.example.volunteerapp.databinding.LoginFragmentBinding
import com.example.volunteerapp.databinding.MapFragmentBinding
import com.example.volunteerapp.domain.VolunteerCenterNetwork
import com.example.volunteerapp.domain.center.GetCenterUseCase
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapFragment:Fragment(R.layout.map_fragment),OnMapReadyCallback {
    lateinit var mGoogleMap: GoogleMap
    lateinit var binding: MapFragmentBinding
    lateinit var volunteerCenterNetwork:VolunteerCenterNetwork
    lateinit var volunteerCenterRepoImpl:VolunteerCenterRepoImpl
    lateinit var getCenterUseCase:GetCenterUseCase
    lateinit var coordinatesCenter:String
    lateinit var centerList:List<VolunteerCenterEntity>
    lateinit var sharedPreferences:SharedPreferences
    private var SHARED_PREF_NAME:String = "dildo"
    private var KEY_NAME:String= "pizdec"
    var result:String? =null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)

        volunteerCenterNetwork = VolunteerCenterNetwork()
        volunteerCenterRepoImpl = VolunteerCenterRepoImpl(volunteerCenterNetwork)
        getCenterUseCase = GetCenterUseCase(volunteerCenterRepoImpl)

        sharedPreferences = (context?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE) ?: null)!!
        result = sharedPreferences.getString(KEY_NAME,null)






        binding = MapFragmentBinding.bind(view)
        var mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.backButton.setOnClickListener {
            var editor:SharedPreferences.Editor =sharedPreferences.edit()
            editor.clear()
            editor.commit()
            Navigation.findNavController(view)
                .navigate(R.id.action_mapFragment_to_loginFragment)

        }


    }

    override fun onMapReady(googleMap:  GoogleMap) {
        mGoogleMap = googleMap
        CoroutineScope(Dispatchers.Main).launch {
            getCenters()
            for(center in centerList){
                var coord = center.coordinates
                var coord1 = coord.split(",")
                if(coord1[0].equals("не указан")== false){
                    var point = LatLng(coord1[0].toDouble(),coord1[1].substring(1).toDouble())
                    mGoogleMap.addMarker(MarkerOptions().position(point).title(center.name))
                }

            }
            mGoogleMap.setOnMarkerClickListener { marker->

                    val position = marker.position
                    val latitude = position.latitude
                    val longitude = position.longitude
                    Log.d("Coordinates","${latitude}, ${longitude}")
                coordinatesCenter = "${latitude},${longitude}"
                Log.d("Coordinates1","${latitude}, ${longitude}")
                false





            }

        }

    }

    private suspend fun getCenters(){
        val result = getCenterUseCase.invoke()
        result.onSuccess {center->
            if (center.isNotEmpty()){
                centerList = center
                for(center in centerList){
                    var coord1 = center.coordinates
                    var coordList = coord1.split(",")
                    Log.d("MyCoord", coordList.toString())

                }


            }
            if (center.isEmpty()){
                Log.d("Zalupa","hui")
            }

        }
        result.onFailure {
            Log.d("MyLog","No coordinates")
        }

    }

}