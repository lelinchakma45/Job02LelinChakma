package com.example.job02lelinchakma.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_profile_register")
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val dob: String,
    val district: String,
    val mobile: String
): Serializable