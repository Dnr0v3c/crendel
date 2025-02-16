package com.example.volunteerapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.volunteerapp.R
import com.example.volunteerapp.databinding.VolunteerItemBinding
import com.example.volunteerapp.profile.ProfileEntity

class VolunteerAdapter:RecyclerView.Adapter<VolunteerAdapter.ViewHolder>() {

    val volunteerItem =ArrayList<ProfileEntity>()

    class ViewHolder(item: View):RecyclerView.ViewHolder(item){
        val binding = VolunteerItemBinding.bind(item)
        fun bind(item:ProfileEntity){
            binding.nameText.text = item.name
            binding.emailText.text =item.email
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.volunteer_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return volunteerItem.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(volunteerItem[position])
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addVolunteer(profileEntity: ProfileEntity){
        volunteerItem.add(profileEntity)
        notifyDataSetChanged()
    }

}