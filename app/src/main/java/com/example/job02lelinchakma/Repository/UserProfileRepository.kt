package com.example.job02lelinchakma.Repository

import androidx.lifecycle.LiveData
import com.example.job02lelinchakma.Database.UserProfileDao
import com.example.job02lelinchakma.Models.UserProfile

class UserProfileRepository(private val userProfileDao: UserProfileDao) {
    fun getUserProfiles(): LiveData<List<UserProfile>> {
        return userProfileDao.getAllUserProfiles()
    }
    suspend fun insert(userProfile: UserProfile) {
        userProfileDao.insert(userProfile)
    }

    suspend fun update(userProfile: UserProfile) {
        userProfileDao.update(userProfile)
    }

    suspend fun delete(userProfile: UserProfile) {
        userProfileDao.delete(userProfile)
    }
}