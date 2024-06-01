package com.example.job02lelinchakma.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.job02lelinchakma.Models.UserProfile
import com.example.job02lelinchakma.R
import com.google.android.material.button.MaterialButton

class ProfileAdapter : ListAdapter<UserProfile, ProfileAdapter.ProfileViewHolder>(DiffCallback()) {

    private var onItemClickListener: ((UserProfile) -> Unit)? = null
    private var onDeleteClickListener: ((UserProfile) -> Unit)? = null
    private var onUpdateClickListener: ((UserProfile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_users, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    fun setOnItemClickListener(listener: (UserProfile) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnDeleteClickListener(listener: (UserProfile) -> Unit) {
        onDeleteClickListener = listener
    }

    fun setOnUpdateClickListener(listener: (UserProfile) -> Unit) {
        onUpdateClickListener = listener
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileName: TextView = itemView.findViewById(R.id.nameEt)
        private val profileEmail: TextView = itemView.findViewById(R.id.emailView)
        private val profileDOB: TextView = itemView.findViewById(R.id.dobView)
        private val profileDistrict: TextView = itemView.findViewById(R.id.districtView)
        private val profileMobile: TextView = itemView.findViewById(R.id.mobileView)
        private val deleteButton: MaterialButton = itemView.findViewById(R.id.btnDelete)
        private val updateButton: MaterialButton = itemView.findViewById(R.id.btnEdit)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val profile = getItem(position)
                    onItemClickListener?.invoke(profile)
                }
            }

            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val profile = getItem(position)
                    onDeleteClickListener?.invoke(profile)
                }
            }

            updateButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val profile = getItem(position)
                    onUpdateClickListener?.invoke(profile)
                }
            }
        }

        fun bind(userProfile: UserProfile) {
            profileName.text = userProfile.name
            profileEmail.text = userProfile.email
            profileDOB.text = userProfile.dob
            profileDistrict.text = userProfile.district
            profileMobile.text = userProfile.mobile
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<UserProfile>() {
        override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem == newItem
        }
    }
}