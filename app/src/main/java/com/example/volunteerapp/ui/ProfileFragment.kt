package com.example.volunteerapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.volunteerapp.R
import com.example.volunteerapp.databinding.ProfileFragmentBinding
import com.example.volunteerapp.ui.modles.ProfileView

class ProfileFragment:Fragment(R.layout.profile_fragment) {

    private lateinit var binding:ProfileFragmentBinding
    private val profileView:ProfileView by activityViewModels()
    private var isPressed:Boolean = false


    @SuppressLint("SuspiciousIndentation")
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

        binding.buttonUpdateProfile.setOnClickListener {
                    if (isPressed==false) {
                        isPressed = true
                        binding.nameEdit.isVisible = true
                        binding.emailEdit.isVisible = true
                        binding.emailText.isVisible = false
                        binding.nameText.isVisible = false
                    }

                else{
                    isPressed=false
                    profileView.updateUser(binding.nameEdit.text.toString(),binding.emailEdit.text.toString())
                        profileView.getUser(binding.username.text.toString())
                    binding.nameEdit.isVisible = false
                    binding.emailEdit.isVisible = false
                        binding.emailEdit.text.clear()
                        binding.nameEdit.text.clear()
                    binding.emailText.isVisible = true
                    binding.nameText.isVisible = true
                }

        }


    }

}