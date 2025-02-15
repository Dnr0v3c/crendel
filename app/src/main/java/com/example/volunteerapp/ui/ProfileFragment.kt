package com.example.volunteerapp.ui

import android.os.Bundle
import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.volunteerapp.R
import com.example.volunteerapp.databinding.ProfileFragmentBinding
import com.example.volunteerapp.ui.modles.ProfileView

class ProfileFragment:Fragment(R.layout.profile_fragment) {

    private lateinit var binding:ProfileFragmentBinding
    private val profileView:ProfileView by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProfileFragmentBinding.bind(view)

        profileView.name.observe(viewLifecycleOwner, Observer {
            binding.nameText.text = it
        })
        profileView.username.observe(viewLifecycleOwner, Observer {
            binding.username.text = it
        })
        profileView.email.observe(viewLifecycleOwner, Observer {
            binding.emailText.text = it
        })

    }
}