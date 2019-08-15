package com.openclassrooms.realestatemanager.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Place::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("placeId"))])

data class Item(

        @PrimaryKey(autoGenerate = true)
        val id: Int?,
        val placeTitle: String?,
        val placePrice: Int?,
        val placeId: Int?

)