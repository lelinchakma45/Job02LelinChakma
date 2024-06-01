package com.example.job02lelinchakma.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.job02lelinchakma.Models.UserProfile

@Database(entities = [UserProfile::class], version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userProfileDao():UserProfileDao
    companion object{
        @Volatile
        private var INSTANCE:UserDatabase?= null
        fun getDatabase(context: Context):UserDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}