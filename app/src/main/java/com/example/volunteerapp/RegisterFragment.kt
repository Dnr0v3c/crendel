package com.example.volunteerapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.volunteerapp.databinding.RegisterFragmentBinding
import com.example.volunteerapp.domain.AuthNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterFragment:Fragment(R.layout.register_fragment) {
    lateinit var authNetwork: AuthNetwork
    lateinit var binding: RegisterFragmentBinding
    var isUserExist: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RegisterFragmentBinding.bind(view)
        authNetwork = AuthNetwork()

        binding.signupButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val result = authNetwork.register(
                    binding.username.text.toString(),
                    binding.name.text.toString(),
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
                result.onSuccess {
                    onLoginActivity()
                }
                result.onFailure {
                    binding.error.text = "Error"
                    binding.error.isVisible = true
                }


            }


        }
    }

    fun onLoginActivity(){
        val intent = Intent(context,MainActivity::class.java)
        startActivity(intent)
    }





    companion object{
        @JvmStatic
        fun newInstance() = RegisterFragment()

    }

}