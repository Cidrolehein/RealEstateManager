package com.openclassrooms.realestatemanager.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(

        @PrimaryKey
        val id: Long,
        val placeTitle: String?,
        val placePrice: Int?
)