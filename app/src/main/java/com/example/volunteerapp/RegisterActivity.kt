package com.example.volunteerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.volunteerapp.databinding.RegisterActivityBinding

class RegisterActivity:AppCompatActivity() {
    lateinit var binding:RegisterActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_register,RegisterFragment.newInstance()).commit()
    }



}