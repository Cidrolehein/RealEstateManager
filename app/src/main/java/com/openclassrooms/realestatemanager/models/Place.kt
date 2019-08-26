package com.openclassrooms.realestatemanager.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(

        @PrimaryKey
        val id: Long,
        val type: String?,
        val placeTitle: String?,
        val placePrice: String,
        val roomNumber: Int?,
        val description: String?,
        val picture: String?,
        val placeAddress: String?,
        //val interest: List<String>?,
        val placeStatus: String?,
        val datePlace: String?,
        val placeAgent: String?
)