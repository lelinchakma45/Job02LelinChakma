package com.example.job02lelinchakma

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.job02lelinchakma.Adapter.ProfileAdapter
import com.example.job02lelinchakma.Dialog.DeleteDialog
import com.example.job02lelinchakma.ViewModels.UserProfileViewModel
import com.example.job02lelinchakma.databinding.ActivityProfileListBinding

class ProfileListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProfileListBinding.inflate(layoutInflater)
    }
    private lateinit var profileViewModel: UserProfileViewModel
    private lateinit var profileAdapter: ProfileAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showData()
        binding.addProfileBtn.setOnClickListener {
            startActivity(Intent(this, ProfileAddActivity::class.java))
        }
    }
    fun showData(){
        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        profileAdapter = ProfileAdapter()

        binding.profileList.adapter = profileAdapter
        binding.profileList.layoutManager = LinearLayoutManager(this)

        profileViewModel.getUserProfiles().observe(this, Observer { profiles ->
            if (profiles.isNotEmpty()){
                profileAdapter.submitList(profiles)
                binding.profileList.visibility = View.VISIBLE
                binding.noDataShow.visibility = View.GONE
            }
            else{
                binding.profileList.visibility = View.GONE
                binding.noDataShow.visibility = View.VISIBLE
            }

        })
        profileAdapter.setOnDeleteClickListener { userProfile ->
            val customDialog = DeleteDialog(this, userProfile) { shouldDelete ->
                if (shouldDelete) {
                    profileViewModel.deleteUserProfile(userProfile)
                }
            }
            customDialog.show()
        }

        profileAdapter.setOnUpdateClickListener { userProfile ->
            val intent = Intent(this@ProfileListActivity, ProfileEditActivity::class.java)
            intent.putExtra("USER_PROFILE", userProfile)
            startActivity(intent)
        }

        binding.addProfileBtn.setOnClickListener {
            val intent = Intent(this@ProfileListActivity, ProfileAddActivity::class.java)
            startActivity(intent)
        }

    }
}