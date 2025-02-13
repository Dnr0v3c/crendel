package com.example.volunteerapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MainActivity : AppCompatActivity(),OnMapReadyCallback {
    lateinit var mGoogleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentNavigation) as NavHostFragment
        val navController = navHostFragment.navController





    }

    fun openFragment(id:Int,fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(id,fragment).commit()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
    }
}