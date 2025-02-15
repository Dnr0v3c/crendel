package com.example.volunteerapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.volunteerapp.R
import com.example.volunteerapp.RegisterActivity
import com.example.volunteerapp.databinding.LoginFragmentBinding
import com.example.volunteerapp.domain.AuthNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment :Fragment(R.layout.login_fragment){
    lateinit var binding:LoginFragmentBinding
    lateinit var authNetwork: AuthNetwork
    lateinit var sharedPreferences: SharedPreferences
    private var SHARED_PREF_NAME:String = "dildo"
    private var KEY_NAME:String= "pizdec"
    var isUserExist:Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        binding = LoginFragmentBinding.bind(view)
        authNetwork = AuthNetwork()

        sharedPreferences =
            (context?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE) ?: null)!!

        var name: String? = sharedPreferences.getString(KEY_NAME, null)

        if (name != null) {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_mapFragment)
        }

        binding.buttonLogin.setOnClickListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        ifUserExist(binding.login.text.toString())
                        if (isUserExist == true) {
                            var result = authNetwork.login(
                                binding.login.text.toString(),
                                binding.password.text.toString()
                            )
                            result.onSuccess {

                                var editor: SharedPreferences.Editor = sharedPreferences.edit()
                                editor.putString(KEY_NAME, binding.login.text.toString())
                                editor.apply()

                                Navigation.findNavController(view)
                                    .navigate(R.id.action_loginFragment_to_mapFragment)

                            }
                            result.onFailure {
                                binding.error.text = "Error"
                            }
                        } else {
                            binding.error.isVisible = true
                            binding.error.setOnClickListener {
                                onRegisterActivity()

                            }

                        }
                    }

            }
        }






    suspend fun ifUserExist(login: String){
        val result = authNetwork.isUserExist(login)
        result.onSuccess { isExist->
            isUserExist = isExist
        }


    }
    fun onRegisterActivity(){
        val intent = Intent(context, RegisterActivity::class.java)
        startActivity(intent)
    }


    suspend fun login(login:String,password:String){
        var result = authNetwork.login(login,password)
        result.onSuccess {


        }
        result.onFailure {
            binding.error.text="Error"
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = LoginFragment()

    }
}