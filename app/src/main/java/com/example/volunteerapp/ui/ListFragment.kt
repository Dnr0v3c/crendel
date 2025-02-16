package com.example.volunteerapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.volunteerapp.R
import com.example.volunteerapp.databinding.FragmentListBinding
import com.example.volunteerapp.ui.adapter.VolunteerAdapter
import com.example.volunteerapp.ui.modles.VolunteerListModel

class ListFragment: Fragment(R.layout.fragment_list) {
    private lateinit var binding:FragmentListBinding
    private val volunteerView:VolunteerListModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        val adapter = VolunteerAdapter()
        binding.rvView.adapter = adapter
        volunteerView.listVolunteer.observe(viewLifecycleOwner, Observer {it->
            for(profileEntity in it){
                adapter.addVolunteer(profileEntity)
            }
        })



    }
}