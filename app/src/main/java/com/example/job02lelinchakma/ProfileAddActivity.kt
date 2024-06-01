package com.example.job02lelinchakma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.job02lelinchakma.Models.UserProfile
import com.example.job02lelinchakma.ViewModels.UserProfileViewModel
import com.example.job02lelinchakma.databinding.ActivityProfileAddBinding

class ProfileAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProfileAddBinding.inflate(layoutInflater)
    }
    private lateinit var profileViewModel: UserProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        binding.signEmailBtn.setOnClickListener {
            val name = binding.userNameEt.text.toString()
            val email = binding.emailEt.text.toString()
            val dob = binding.userBODEt.text.toString()
            val district = binding.userHomeDistrict.text.toString()
            val mobile = binding.userPhone.text.toString()

            val userProfile = UserProfile(name = name, email = email, dob = dob, district = district, mobile = mobile)
            profileViewModel.insertUserProfile(userProfile)

            finish()
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}